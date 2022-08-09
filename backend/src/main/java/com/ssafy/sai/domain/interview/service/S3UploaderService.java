package com.ssafy.sai.domain.interview.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.ssafy.sai.domain.interview.domain.InterviewVideo;
import com.ssafy.sai.domain.interview.dto.CreateInterviewVideoResponse;
import com.ssafy.sai.domain.interview.dto.DeleteInterviewVideoRequest;
import com.ssafy.sai.domain.interview.exception.InterviewException;
import com.ssafy.sai.domain.interview.exception.InterviewExceptionType;
import com.ssafy.sai.domain.interview.repository.InterviewVideoRepository;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class S3UploaderService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;
    private final MemberRepository memberRepository;
    private final InterviewVideoRepository savedInterviewVideoRepository;


    public CreateInterviewVideoResponse uploadFileS3(Long id, MultipartFile multipartFile) {

        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));


        String videoName = createFileName(multipartFile.getOriginalFilename());
        String videoUrl;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, videoName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            videoUrl = amazonS3.getUrl(bucket, videoName).toString();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }

        // 서비스에서 면접정보 먼저 저장하고 -> 면접 영상 저장하는 방식으로 해야될거같다

        // db에 저장하기
        InterviewVideo interviewVideo = InterviewVideo.builder().member(findMember).videoUrl(videoUrl).videoName(videoName).build();
        savedInterviewVideoRepository.save(interviewVideo);

        return new CreateInterviewVideoResponse(id, videoName, videoUrl);
    }

    public void deleteFileS3(Long memberId, DeleteInterviewVideoRequest request) throws MemberException {
        // 멤버 있는지 확인 -> 에러처리
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        // 먼저, s3에 파일이 있는지부터 확인하기.
        if (!amazonS3.doesObjectExist(bucket, request.getVideoName())) {
            // 존재하지 않으니까 예외 발생시키기
            throw new InterviewException(InterviewExceptionType.NOT_FOUND_S3_VIDEO);
        }

        // S3안에 있는 영상 삭제
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, request.getVideoName()));

        // s3안에 파일 잘 지워졌는지 다시 한번 확인
        if (amazonS3.doesObjectExist(bucket, request.getVideoName())) {
            // 존재하니까 예외 발생시키기
            throw new InterviewException(InterviewExceptionType.STILL_EXIST_S3_VIDEO);
        }

        // 잘 지워졌으니까 다음 로직 실행
        // DB에 있는 영상 컬럼도 지우기 - 외래키 같이 삭제 주의!
        // 면접정보 PK도 프론트에서 보내줘야할듯

        // 삭제 잘 됐는지 안됐는지 확인할 수 있는 키 있으면 같이 넘겨주고 아니면 void

    }

    private String createFileName(String fileName) { // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌립니다.
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) { // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다.
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }



}
