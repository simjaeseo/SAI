package com.ssafy.sai.domain.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateInterviewVideoResponse {

    private Long id;
    private String videoName;
    private String videoUrl;


}
