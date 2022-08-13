package com.ssafy.sai.domain.interview.dto.response;

import com.ssafy.sai.domain.interview.domain.FeedbackCompleteStatus;
import com.ssafy.sai.domain.interview.domain.FeedbackRequestStatus;
import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InterviewInfoResponse {

    private Long id;
    private Long consultantId;
    private String category;
    private String detail;
    private LocalDate interviewDate;
    private FeedbackRequestStatus feedbackRequestStatus;
    private FeedbackCompleteStatus feedbackCompleteStatus;

    public InterviewInfoResponse(InterviewInfo interviewInfo){
        id = interviewInfo.getId();
        consultantId =  interviewInfo.getMemberConsultant().getId();
        category = interviewInfo.getCategory();
        detail = interviewInfo.getDetail();
        interviewDate = interviewInfo.getInterviewDate();
        feedbackRequestStatus = interviewInfo.getFeedbackRequestStatus();
        feedbackCompleteStatus = interviewInfo.getFeedbackCompleteStatus();
    }

}
