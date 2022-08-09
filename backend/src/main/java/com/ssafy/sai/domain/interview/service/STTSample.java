package com.ssafy.sai.domain.interview.service;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.longrunning.OperationTimedPollAlgorithm;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import com.google.cloud.speech.v1.*;
import org.springframework.stereotype.Service;
import org.threeten.bp.Duration;

import java.util.List;

@Service
public class STTSample {

    /** Demonstrates using the Speech API to transcribe an audio file. */
    public void STT(String gsutillUrl) throws Exception {
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
                            .setEncoding(RecognitionConfig.AudioEncoding.FLAC).setAudioChannelCount(2)
                            .setLanguageCode("ko-KR")
                            .setSampleRateHertz(48000)
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri("gs://sai-ssafy/2022-08-07 22-00-45 (online-audio-converter.com).flac").build();

            // Use non-blocking call for getting file transcription
            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
                    speech.longRunningRecognizeAsync(config, audio);
            while (!response.isDone()) {
                System.out.println("Waiting for response...");
                Thread.sleep(10000);
            }

            List<SpeechRecognitionResult> results = response.get().getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s\n", alternative.getTranscript());
            }
        }

    }
}
