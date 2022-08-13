package com.ssafy.sai.domain.interview.service;


import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.longrunning.OperationTimedPollAlgorithm;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.ssafy.sai.domain.interview.domain.*;
import com.ssafy.sai.domain.interview.dto.request.CreateInterviewInfoRequest;
import com.ssafy.sai.domain.interview.repository.InterviewInfoRepository;
import com.ssafy.sai.domain.interview.repository.InterviewVideoRepository;
import com.ssafy.sai.domain.interview.repository.UseInterviewQuestionRepository;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.repository.MemberRepository;
import com.ssafy.sai.domain.schedule.domain.Schedule;
import com.ssafy.sai.domain.schedule.exception.ScheduleException;
import com.ssafy.sai.domain.schedule.exception.ScheduleExceptionType;
import com.ssafy.sai.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.threeten.bp.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Transactional
public class GcsService {
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final InterviewInfoRepository interviewInfoRepository;
    private final InterviewVideoRepository interviewVideoRepository;
    private final UseInterviewQuestionRepository useInterviewQuestionRepository;

    // gcs 업로드

    /**
     * @param id                  사용자pk
     * @param request             일정id, 피드백요청 유무, 컨설턴트id, 면접영상url 배열(openvidu server 안), 질문배열
     * @param saveInterviewInfo   면접 정보 엔티티
     * @param audioMultipartFiles .flac 멀티파트파일
     * @param openviduVideoNames  openvidu server에 저장되어있는 mp4 파일 이름 리스트
     * @param flacAudioNames      .flac 오디오 파일 이름 리스트
     * @param S3videoUrlList      S3에 저장된 면접영상에 접근할수있는 url 리스트
     * @throws IOException
     * @throws RuntimeException
     * @throws ExecutionException
     * @throws InterruptedException
     * @메소드 gcs 업로드 메소드
     */
    public void uploadFileGcs(Long id, CreateInterviewInfoRequest request, InterviewInfo saveInterviewInfo, List<MultipartFile> audioMultipartFiles, List<String> openviduVideoNames, List<String> flacAudioNames, List<String> S3videoUrlList, List<String> S3videoNameList) throws IOException, RuntimeException, ExecutionException, InterruptedException {

        List<String> gcsUrls = new ArrayList<>();

        String keyFileName = "psychic-habitat-358714-81bd61376e0d.json";
        InputStream keyFile = ResourceUtils.getURL("classpath:" + keyFileName).openStream();

        Storage storage = StorageOptions.newBuilder().setProjectId("psychic-habitat-358714")
                // Key 파일 수동 등록
                .setCredentials(GoogleCredentials.fromStream(keyFile))
                .build().getService();

        int index = 0;

        for (MultipartFile audioMultipartFile : audioMultipartFiles) {
            String audioName = createFileName(audioMultipartFile.getOriginalFilename());
            BlobInfo blobInfo = storage.create(
                    BlobInfo.newBuilder("sai-ssafy", flacAudioNames.get(index)).setContentType("audio/flac").build(), //get original file name
                    audioMultipartFile.getBytes()
            );
            String gsutillUrl = "gs://sai-ssafy/" + flacAudioNames.get(index); // google speech API 를 사용하기위한 gsutill URL

            String gcsUrl = "https://storage.googleapis.com/sai-ssafy/" + audioName;
            gcsUrls.add(gcsUrl);

            STT(id, request, saveInterviewInfo, gsutillUrl, gcsUrls.get(index), openviduVideoNames, flacAudioNames, S3videoUrlList.get(index), S3videoNameList.get(index), index);
            index++;
        }
    }


    /**
     * @param fileName 파일 이름
     * @return
     * @메소드 파일 이름 랜덤하게 지어주는 함수
     */
    private String createFileName(String fileName) { // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌립니다.
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    /**
     * @param fileName 파일 이름
     * @return
     * @메소드 file 형식이 잘못된 경우를 확인하기 위한 함수 (파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단)
     */
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }

    /**
     * @param id                 사용자pk
     * @param request            일정id, 피드백요청 유무, 컨설턴트id, 면접영상url 배열(openvidu server 안), 질문배열
     * @param saveInterviewInfo  면접 정보 엔티티
     * @param gsutillUrl         gcs에 저장된 flac에 접근할 수 있는 gsutill url
     * @param gcsUrl             gcs에 저장된 flac에 접근할 수 있는 url
     * @param openviduVideoNames openvidu server에 저장되어있는 mp4 파일 이름 리스트
     * @param flacAudioNames     .flac 오디오 파일 이름 리스트
     * @param S3videoUrl         S3에 저장된 면접영상에 접근할수있는 url
     * @param index              인덱스
     * @throws IOException
     * @throws InterruptedException
     * @throws ExecutionException
     * @메소드 stt 함수
     */
    public void STT(Long id, CreateInterviewInfoRequest request, InterviewInfo saveInterviewInfo, String gsutillUrl, String gcsUrl, List<String> openviduVideoNames, List<String> flacAudioNames, String S3videoUrl, String S3videoName, int index) throws IOException, InterruptedException, ExecutionException {


        // Configure polling algorithm
        SpeechSettings.Builder speechSettings = SpeechSettings.newBuilder();
        TimedRetryAlgorithm timedRetryAlgorithm =
                OperationTimedPollAlgorithm.create(
                        RetrySettings.newBuilder()
                                .setInitialRetryDelay(Duration.ofMillis(500L))
                                .setRetryDelayMultiplier(1.5)
                                .setMaxRetryDelay(Duration.ofMillis(5000L))
                                .setInitialRpcTimeout(Duration.ZERO) // ignored
                                .setRpcTimeoutMultiplier(1.0) // ignored
                                .setMaxRpcTimeout(Duration.ZERO) // ignored
                                .setTotalTimeout(Duration.ofHours(24L)) // set polling timeout to 24 hours
                                .build());
        speechSettings.longRunningRecognizeOperationSettings().setPollingAlgorithm(timedRetryAlgorithm);

        // Instantiates a client with GOOGLE_APPLICATION_CREDENTIALS
        try (SpeechClient speech = SpeechClient.create(speechSettings.build())) {

            // Configure remote file request for FLAC
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.FLAC).setAudioChannelCount(1)
                            .setLanguageCode("ko-KR")
                            .setSampleRateHertz(16000)
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gsutillUrl).build();

            // Use non-blocking call for getting file transcription
            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
                    speech.longRunningRecognizeAsync(config, audio);
            while (!response.isDone()) {
                System.out.println("Waiting for response...");
                Thread.sleep(10000);
            }

            List<SpeechRecognitionResult> results = response.get().getResultsList();

            String stt = "";
            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                stt += alternative.getTranscript();
                System.out.printf("Transcription: %s\n", alternative.getTranscript());
            }


            // 면접 영상 db에 저장하기
            InterviewVideo interviewVideo = InterviewVideo.builder()
                    .interviewInfo(saveInterviewInfo)
                    .videoUrl(S3videoUrl)
                    .videoName(S3videoName)
                    .audioUrl(gcsUrl)
                    .audioName(flacAudioNames.get(index))
                    .stt(stt)
                    .wrongPostureCount(request.getWrongPostureCount()).build();


            //사용한 면접 질문 db에 저장하기
            UseInterviewQuestion useInterviewQuestion = UseInterviewQuestion.builder()
                    .question(request.getQuestions().get(index)).build();

            useInterviewQuestion.addInterviewVideo(interviewVideo);

            //cascade를 통해 interviewVideo도 자동 저장
            useInterviewQuestionRepository.save(useInterviewQuestion);


            for (String openviduVideoName : openviduVideoNames) {
                File inteviewMp4File = new File(openviduVideoName);
                if (inteviewMp4File.exists()) { //파일존재여부확인
                    inteviewMp4File.delete();
                }
            }

            for (String flacAudioName : flacAudioNames) {
                File inteviewFlacFile = new File(flacAudioName);
                if (inteviewFlacFile.exists()) { //파일존재여부확인
                    inteviewFlacFile.delete();
                }
            }


        }
    }


    public void deleteFileGcs(List<InterviewVideo> findInterviewVideos) throws IOException {

        String keyFileName = "psychic-habitat-358714-81bd61376e0d.json";
        InputStream keyFile = ResourceUtils.getURL("classpath:" + keyFileName).openStream();

        Storage storage = StorageOptions.newBuilder().setProjectId("psychic-habitat-358714")
                // Key 파일 수동 등록
                .setCredentials(GoogleCredentials.fromStream(keyFile))
                .build().getService();


        for (InterviewVideo findInterviewVideo : findInterviewVideos) {
            storage.delete(BlobId.of("sai-ssafy", findInterviewVideo.getAudioName()));

        }


    }
}
