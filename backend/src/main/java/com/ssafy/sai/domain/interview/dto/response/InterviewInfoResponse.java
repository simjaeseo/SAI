package com.ssafy.sai.domain.interview.dto.response;


import com.ssafy.sai.domain.interview.domain.FeedbackCompleteStatus;
import com.ssafy.sai.domain.interview.domain.FeedbackRequestStatus;

import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InterviewInfoResponse {

    private Long id;
    private String consultantName;
    private String category;
    private String detail;
    private LocalDate interviewDate;
    private FeedbackRequestStatus feedbackRequestStatus;
    private FeedbackCompleteStatus feedbackCompleteStatus;

    public InterviewInfoResponse(InterviewInfo interviewInfo){
        id = interviewInfo.getId();
        consultantName = interviewInfo.getMemberConsultant()
                == null ? null : interviewInfo.getMemberConsultant().getName();
        category = interviewInfo.getCategory();
        detail = interviewInfo.getDetail();
        interviewDate = interviewInfo.getInterviewDate();
        feedbackRequestStatus = interviewInfo.getFeedbackRequestStatus();
        feedbackCompleteStatus = interviewInfo.getFeedbackCompleteStatus();
    }
}
