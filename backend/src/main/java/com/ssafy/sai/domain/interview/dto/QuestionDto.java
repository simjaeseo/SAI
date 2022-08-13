package com.ssafy.sai.domain.interview.dto;

import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.interview.domain.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionDto {

    private QuestionDto.data interviewQuestion;

    @Getter
    @AllArgsConstructor
    public static class data{
        private Long id;
        private String question;
        private String questionType;
        private String questionDetailType;
    }
}
