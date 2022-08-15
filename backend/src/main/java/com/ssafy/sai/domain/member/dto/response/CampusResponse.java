package com.ssafy.sai.domain.member.dto.response;

import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import lombok.Data;

@Data
public class CampusResponse {

    private String city;
    private Integer classNumber;

    public CampusResponse() {}

    public CampusResponse(String city, Integer classNumber) {
        this.city = city;
        this.classNumber = classNumber;
    }

    public CampusResponse(InterviewInfo interviewInfo) {
        this.city = interviewInfo.getMemberStudent().getCampus().getCity();
        this.classNumber = interviewInfo.getMemberStudent().getCampus().getClassNumber();
    }
}
