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

    private CustomInterviewQuestion customInterviewQuestion;

    private QuestionDto.data interviewQuestion;

    @Getter
    @AllArgsConstructor
    public static class data{
        private Long id;
        private String question;
        private QuestionType questionType;

    }
}
