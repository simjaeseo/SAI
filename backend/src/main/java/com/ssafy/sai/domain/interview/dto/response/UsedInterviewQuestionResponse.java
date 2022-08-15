package com.ssafy.sai.domain.interview.dto.response;

import com.ssafy.sai.domain.interview.domain.InterviewVideo;
import lombok.Data;

@Data
public class UsedInterviewQuestionResponse {

    private Long id;
    private String question;

    public UsedInterviewQuestionResponse(InterviewVideo interviewVideo) {
        this.id = interviewVideo.getUsedInterviewQuestion().getId();
        this.question = interviewVideo.getUsedInterviewQuestion().getQuestion();
    }
}
