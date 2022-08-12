package com.ssafy.sai.domain.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateInterviewVideoResponse {

    private Long id;
    private List<String> videoNameList;
    private List<String> videoUrlList;


}
