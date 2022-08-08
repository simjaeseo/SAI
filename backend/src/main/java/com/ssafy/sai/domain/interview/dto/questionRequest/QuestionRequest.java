package com.ssafy.sai.domain.interview.dto.questionRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionRequest {

    private String questionType;

    private String questionDetailType;
}
