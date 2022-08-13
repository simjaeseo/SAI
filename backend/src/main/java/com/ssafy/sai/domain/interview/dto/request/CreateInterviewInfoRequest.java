package com.ssafy.sai.domain.interview.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateInterviewInfoRequest {

    private Long scheduleId;
    private String feedbackRequest;
    private String consultantId;
    private String wrongPostureCount;
    private List<String> interviewVideoUrl;
    private List<String> questions;

}
