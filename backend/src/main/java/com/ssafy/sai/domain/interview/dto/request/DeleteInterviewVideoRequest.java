package com.ssafy.sai.domain.interview.dto.request;

import lombok.Data;

@Data
public class DeleteInterviewVideoRequest {

    private Long videoId;

    private String videoName;
}
