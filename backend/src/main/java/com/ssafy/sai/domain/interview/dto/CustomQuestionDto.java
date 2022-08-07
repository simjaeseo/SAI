package com.ssafy.sai.domain.interview.dto;

import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomQuestionDto {

    private CustomInterviewQuestion customInterviewQuestion;


    @Getter
    @AllArgsConstructor
    public static class data{
        private Long id;
        private String question;
        private Member member;

    }
}