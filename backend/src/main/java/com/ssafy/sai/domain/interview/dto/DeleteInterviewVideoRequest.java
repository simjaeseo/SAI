package com.ssafy.sai.domain.interview.dto;

import lombok.Data;

@Data
public class DeleteInterviewVideoRequest {

    private Long videoId;

    private String videoName;
}
