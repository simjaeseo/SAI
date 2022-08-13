package com.ssafy.sai.domain.interview.dto.response;

import com.ssafy.sai.domain.interview.domain.InterviewVideo;
import com.ssafy.sai.domain.interview.domain.UsedInterviewQuestion;
import lombok.Data;

@Data
public class InterviewVideoResponse {

    private Long videoId;
    private String videoUrl;
    private String feedback;
    private UsedInterviewQuestion usedInterviewQuestion;

    public InterviewVideoResponse(InterviewVideo interviewVideo) {
        videoId = interviewVideo.getId();
        videoUrl = interviewVideo.getVideoUrl();
        feedback = interviewVideo.getFeedback();
        usedInterviewQuestion = interviewVideo.getUsedInterviewQuestion();
    }
}
