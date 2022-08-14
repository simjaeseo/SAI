package com.ssafy.sai.domain.interview.dto.response;


import com.ssafy.sai.domain.interview.domain.FeedbackCompleteStatus;
import com.ssafy.sai.domain.interview.domain.FeedbackRequestStatus;

import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import com.ssafy.sai.domain.member.dto.response.CampusResponse;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InterviewInfoResponse {

    private Long id;
    private Long studentId;
    private String studentName;
    private CampusResponse campus;
    private String category;
    private String detail;
    private LocalDate interviewDate;
    private FeedbackRequestStatus feedbackRequestStatus;
    private FeedbackCompleteStatus feedbackCompleteStatus;

    public InterviewInfoResponse(InterviewInfo interviewInfo){
        id = interviewInfo.getId();
        studentId = interviewInfo.getMemberStudent().getId();
        studentName = interviewInfo.getMemberStudent().getName();
        campus = new CampusResponse(interviewInfo);
        category = interviewInfo.getCategory();
        detail = interviewInfo.getDetail();
        interviewDate = interviewInfo.getInterviewDate();
        feedbackRequestStatus = interviewInfo.getFeedbackRequestStatus();
        feedbackCompleteStatus = interviewInfo.getFeedbackCompleteStatus();
    }
}
