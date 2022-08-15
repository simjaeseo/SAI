package com.ssafy.sai.domain.interview.dto.request;

import com.ssafy.sai.domain.member.domain.Member;
import lombok.Data;

@Data
public class CustomQuestionRequest {

    private String question;
    private Long memberId;

    public CustomQuestionRequest (String question, Long memberId) {
        this.question = question;
        this.memberId = memberId;
    }
}
