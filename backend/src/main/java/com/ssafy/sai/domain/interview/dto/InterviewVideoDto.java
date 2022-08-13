package com.ssafy.sai.domain.interview.dto;

import com.ssafy.sai.domain.interview.domain.InterviewVideo;
import com.ssafy.sai.domain.interview.domain.UseInterviewQuestion;
import lombok.Data;

@Data
public class InterviewVideoDto {

    private Long videoId;
    private String videoUrl;
    private String feedback;
    private UseInterviewQuestion useInterviewQuestion;

    public InterviewVideoDto(InterviewVideo interviewVideo) {
        videoId = interviewVideo.getId();
        videoUrl = interviewVideo.getVideoUrl();
        feedback = interviewVideo.getFeedback();
        useInterviewQuestion = interviewVideo.getUseInterviewQuestion();
    }
}
