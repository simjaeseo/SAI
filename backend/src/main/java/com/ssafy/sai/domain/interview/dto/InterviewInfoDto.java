package com.ssafy.sai.domain.interview.dto;

import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InterviewInfoDto {

    private Long infoId;
    private String name;
    private LocalDate interviewDate;
    private String category;
    private String detail;

    public InterviewInfoDto(InterviewInfo interviewInfo) {
        infoId = interviewInfo.getId();
        name = interviewInfo.getMemberStudent().getName();
        interviewDate = interviewInfo.getInterviewDate();
        category = interviewInfo.getCategory();
        detail = interviewInfo.getDetail();
    }
}
