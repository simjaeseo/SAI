package com.ssafy.sai.domain.interview.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import com.ssafy.sai.domain.interview.domain.InterviewVideo;
import com.ssafy.sai.domain.interview.dto.request.CreateInterviewInfoRequest;
import com.ssafy.sai.domain.interview.dto.request.DeleteInterviewVideoRequest;
import com.ssafy.sai.domain.interview.exception.InterviewException;
import com.ssafy.sai.domain.interview.exception.InterviewExceptionType;
import com.ssafy.sai.domain.interview.repository.InterviewVideoRepository;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.repository.MemberRepository;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Transactional
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;
    private final MemberRepository memberRepository;
    private final InterviewVideoRepository savedInterviewVideoRepository;
    private final GcsService gcsService;

    /**
     * @메소드 S3에 파일 업로드하는 서비스
     * @param id 사용자pk
     * @param request 일정id, 피드백요청 유무, 컨설턴트id, 면접영상url 배열(openvidu server 안), 질문배열
     * @param saveInterviewInfo 면접 정보 엔티티
     * @throws RuntimeException
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Async
    public void uploadFileS3(Long id, CreateInterviewInfoRequest request, InterviewInfo saveInterviewInfo) throws RuntimeException, IOException, ExecutionException, InterruptedException {

//        1. 오픈비두 서버 url을 File로 가져옴
//        2. File을 MultipartFile로 변환후 리스트에 저장
//        3. 하나씩 빼면서 s3에 업로드
//        4. // s3url, s3name db에 저장?
//        5. 영상 MultipartFile을 File .flac으로 변환
//        6. File .flac -> MultipartFile .flac으로 변환
//        7. GCS에 저장
//        8. stt


        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        // openvidu server url에서 뽑아온 .mp4파일을 multipartFile로 변환한 것들 저장하는 List
        List<MultipartFile> openviduVideoMultipartFileList = new ArrayList<>();
        // openvidu server 안에 저장되어있는 영상 url
        URL url;

        //s3에 저장된 파일 이름 저장하는 List
        List<String> S3videoNameList = new ArrayList<>();
        //s3에 저장된 파일 url 저장하는 List
        List<String> S3videoUrlList = new ArrayList<>();

        // .flac 파일을 multipartfile 형태로 저장하는 List
        List<MultipartFile> audioMultipartFiles = new ArrayList<>();

        List<String> openviduVideoNames = new ArrayList<>();
        List<String> flacAudioNames = new ArrayList<>();

        // interviewVideoUrl에 접근해서 file 타입으로 mp4 만들기
        for (String openviduVideoUrl : request.getInterviewVideoUrl()) {

            String[] openviduVideoNameSplit = openviduVideoUrl.split("/");
            String openviduVideoName = openviduVideoNameSplit[6];
            openviduVideoNames.add(openviduVideoName);

            // 파일 선언
            File openviduVideoFile = new File(openviduVideoName);

            url = new URL(openviduVideoUrl);

            // url에 접근하기 위해 request 만들기
            CloseableHttpClient httpClient = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet(url.toString());
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.11 Safari/537.36");
            httpGet.addHeader("Referer", "https://www.google.com");

            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("OPENVIDUAPP", "MY_SECRET");
            credsProvider.setCredentials(AuthScope.ANY, credentials);

            HttpClientContext context = HttpClientContext.create();
            context.setCredentialsProvider(credsProvider);

            CloseableHttpResponse httpResponse = httpClient.execute(httpGet, context);
            HttpEntity imageEntity = httpResponse.getEntity();

            if (imageEntity != null) {
                FileUtils.copyInputStreamToFile(imageEntity.getContent(), openviduVideoFile);
            }

            httpGet.releaseConnection();

            // file -> MultipartFile 변환
            DiskFileItem fileItem = null;
            fileItem = new DiskFileItem("file", Files.probeContentType(openviduVideoFile.toPath()), false, openviduVideoFile.getName(), (int) openviduVideoFile.length(), openviduVideoFile.getParentFile());

            InputStream input = new FileInputStream(openviduVideoFile);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);

            MultipartFile openviduVideoMultipartFile = new CommonsMultipartFile(fileItem);


            openviduVideoMultipartFileList.add(openviduVideoMultipartFile);
            // 반복문 끝
        }

        // 면접 영상 MultipartFileList를 하나씩 빼서 s3에 업로드 후 flac 파일 변환함
        openviduVideoMultipartFileList.forEach(openviduVideoFile -> {

            // s3에 업로드
            // 실제 s3에 저장되는 영상 이름
            String videoName = createFileName(openviduVideoFile.getOriginalFilename());
            String videoUrl;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(openviduVideoFile.getSize());
            objectMetadata.setContentType(openviduVideoFile.getContentType());

            try (InputStream inputStream = openviduVideoFile.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, videoName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
                videoUrl = amazonS3.getUrl(bucket, videoName).toString();
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }
            S3videoNameList.add(videoName);
            S3videoUrlList.add(videoUrl);


            int pointIndex = videoName.indexOf(".");
            String audioName = videoName.substring(0, pointIndex);

            Encoder encoder = new Encoder();

            File source = new File(openviduVideoFile.getOriginalFilename());

            try {
                source.createNewFile();
                FileOutputStream fos = new FileOutputStream(source);
                fos.write(openviduVideoFile.getBytes());
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            File target = new File(audioName + ".flac");
            flacAudioNames.add(audioName + ".flac");

            // 오디오 포맷 속성 정의. Google speech to text api 동작 조건
            AudioAttributes audio = new AudioAttributes();
            audio.setSamplingRate(16000); // 샘플 레이트
            audio.setChannels(1); // Mono 채널로 설정해야 speech to text api 사용 가능
            audio.setCodec("flac");  // 코덱 조건

            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("flac"); // 포맷 설정
            attrs.setAudioAttributes(audio);

            try {
                encoder.encode(source, target, attrs);
            } catch (EncoderException e) {
                throw new RuntimeException(e);
            }

            ////////////////////////////
            // file -> MultipartFile 변환
            DiskFileItem fileItem = null;
            try {
                fileItem = new DiskFileItem("file", Files.probeContentType(target.toPath()), false, target.getName(), (int) target.length(), target.getParentFile());

                InputStream input = new FileInputStream(target);
                OutputStream os = fileItem.getOutputStream();
                IOUtils.copy(input, os);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            MultipartFile audioMultipartFile = new CommonsMultipartFile(fileItem);
            audioMultipartFiles.add(audioMultipartFile);
            ///////////////////////////////

            if (source.exists()) { //파일존재여부확인
                source.delete();
            }

        });


        //gcs에 업로드
        gcsService.uploadFileGcs(id, request, saveInterviewInfo, audioMultipartFiles, openviduVideoNames, flacAudioNames, S3videoUrlList, S3videoNameList);


    }

    public void deleteFileS3(List<InterviewVideo> findInterviewVideos) throws MemberException {
        // 멤버 있는지 확인 -> 에러처리
//        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        for (InterviewVideo findInterviewVideo : findInterviewVideos) {
            System.out.println("findInterviewVideo.getVideoName() = " + findInterviewVideo.getVideoName());
            // 먼저, s3에 파일이 있는지부터 확인하기.
            if (!amazonS3.doesObjectExist(bucket, findInterviewVideo.getVideoName())) {
                // 존재하지 않으니까 예외 발생시키기
                throw new InterviewException(InterviewExceptionType.NOT_FOUND_S3_VIDEO);
            }

            // S3안에 있는 영상 삭제
            amazonS3.deleteObject(new DeleteObjectRequest(bucket, findInterviewVideo.getVideoName()));

            // s3안에 파일 잘 지워졌는지 다시 한번 확인
            if (amazonS3.doesObjectExist(bucket, findInterviewVideo.getVideoName())) {
                // 존재하니까 예외 발생시키기
                throw new InterviewException(InterviewExceptionType.STILL_EXIST_S3_VIDEO);
            }

        }

        // 잘 지워졌으니까 다음 로직 실행
        // DB에 있는 영상 컬럼도 지우기 - 외래키 같이 삭제 주의!
        // 면접정보 PK도 프론트에서 보내줘야할듯

        // 삭제 잘 됐는지 안됐는지 확인할 수 있는 키 있으면 같이 넘겨주고 아니면 void

    }

    /**
     * @메소드 파일 이름 랜덤하게 지어주는 함수
     * @param fileName 파일 이름
     * @return
     */
    private String createFileName(String fileName) { // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌립니다.
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    /**
     * @메소드 file 형식이 잘못된 경우를 확인하기 위한 함수 (파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단)
     * @param fileName 파일 이름
     * @return
     */
    private String getFileExtension(String fileName) { // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다.
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }


}
