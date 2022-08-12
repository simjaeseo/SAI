package com.ssafy.sai.domain.interview.service;


import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.longrunning.OperationTimedPollAlgorithm;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.*;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.ssafy.sai.domain.interview.domain.*;
import com.ssafy.sai.domain.interview.dto.CreateInterviewInfoRequest;
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
    public void uploadFileGcs(Long id, CreateInterviewInfoRequest request, InterviewInfo saveInterviewInfo, List<MultipartFile> audioMultipartFiles, List<String> openviduVideoNames, List<String> flacAudioNames, List<String> S3videoUrlList) throws IOException {
        String keyFileName = "psychic-habitat-358714-81bd61376e0d.json";
        InputStream keyFile = ResourceUtils.getURL("classpath:" + keyFileName).openStream();


        Storage storage = StorageOptions.newBuilder().setProjectId("psychic-habitat-358714")
                // Key 파일 수동 등록
                .setCredentials(GoogleCredentials.fromStream(keyFile))
                .build().getService();

        List<String> gcsUrls = new ArrayList<>();
        int index = 0;
        for (MultipartFile audioMultipartFile : audioMultipartFiles) {
            String audioName = createFileName(audioMultipartFile.getOriginalFilename());
            try {
                BlobInfo blobInfo = storage.create(
                        BlobInfo.newBuilder("sai-ssafy", audioName).setContentType("audio/flac").build(), //get original file name
                        audioMultipartFile.getBytes()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String gsutillUrl = "gs://sai-ssafy/" + audioName; // google speech API 를 사용하기위한 gsutill URL

            String gcsUrl = "https://storage.googleapis.com/sai-ssafy/" + audioName;
            gcsUrls.add(gcsUrl);
            // 내 생각에 이걸 저장할때 해도 될지 걱정... 마이페이지에서 동영상 클릭했을때 혹은 리턴하고 나서 하는게 맞을거같은데..
            try {
                STT(id, request, saveInterviewInfo, gsutillUrl, gcsUrls.get(index), openviduVideoNames, flacAudioNames, S3videoUrlList.get(index),  index);
                index++;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
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


    /**
     * Demonstrates using the Speech API to transcribe an audio file.
     */
    public void STT(Long id, CreateInterviewInfoRequest request, InterviewInfo saveInterviewInfo, String gsutillUrl, String gcsUrl, List<String> openviduVideoNames, List<String> flacAudioNames, String S3videoUrl,  int index) throws Exception {


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

            //사용한 면접 질문 넣기
            UseInterviewQuestion useInterviewQuestion = UseInterviewQuestion.builder()
                    .question(request.getQuestions().get(index)).build();

            useInterviewQuestionRepository.save(useInterviewQuestion);


            // db에 저장하기
            InterviewVideo interviewVideo = InterviewVideo.builder()
                    .interviewInfo(saveInterviewInfo)
                    .useInterviewQuestion(useInterviewQuestion)
                    .videoUrl(S3videoUrl)
                    .audioUrl(gcsUrl)
                    .stt(stt)
                    .wrongPostureCount(request.getWrongPostureCount()).build();

            interviewVideoRepository.save(interviewVideo);


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

}

