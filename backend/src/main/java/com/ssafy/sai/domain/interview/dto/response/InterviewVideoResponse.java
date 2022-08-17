package com.ssafy.sai.domain.interview.dto.response;

import com.ssafy.sai.domain.interview.domain.InterviewVideo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class InterviewVideoResponse {

    private Long videoId;
    private String videoUrl;
    private String audioUrl;
    private String feedback;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;
    private String stt;
    private Double emotionRatio;
    private Long wrongPostureCount;
    private UsedInterviewQuestionResponse usedInterviewQuestion;

    public InterviewVideoResponse(InterviewVideo interviewVideo) {
        videoId = interviewVideo.getId();
        videoUrl = interviewVideo.getVideoUrl();
        audioUrl = interviewVideo.getAudioUrl();
        feedback = interviewVideo.getFeedback();
        stt = interviewVideo.getStt();
        createdDate = interviewVideo.getCreatedDate();
        wrongPostureCount = interviewVideo.getWrongPostureCount();
        emotionRatio = interviewVideo.getEmotionRatio();
        usedInterviewQuestion = new UsedInterviewQuestionResponse(interviewVideo);
    }
}
