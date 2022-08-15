package com.ssafy.sai.domain.interview.dto.response;

import com.ssafy.sai.domain.interview.domain.InterviewVideo;
import com.ssafy.sai.domain.interview.domain.UsedInterviewQuestion;
import lombok.Data;

import java.util.List;

@Data
public class InterviewVideoResponse {

    private Long videoId;
    private String videoUrl;
    private String audioUrl;
    private String feedback;
    private String stt;
    private Long wrongPostureCount;
    private Double emotionRatio;
    private UsedInterviewQuestion usedInterviewQuestion;

    public InterviewVideoResponse(InterviewVideo interviewVideo) {
        videoId = interviewVideo.getId();
        videoUrl = interviewVideo.getVideoUrl();
        audioUrl = interviewVideo.getAudioUrl();
        feedback = interviewVideo.getFeedback();
        stt = interviewVideo.getStt();
        wrongPostureCount = interviewVideo.getWrongPostureCount();
        emotionRatio = interviewVideo.getEmotionRatio();
        usedInterviewQuestion = interviewVideo.getUsedInterviewQuestion();
    }
}
