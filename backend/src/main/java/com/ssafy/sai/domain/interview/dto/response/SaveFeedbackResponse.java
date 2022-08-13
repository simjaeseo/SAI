package com.ssafy.sai.domain.interview.dto.response;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class SaveFeedbackResponse {
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createDate;
    private String feedback;

    public SaveFeedbackResponse(LocalDateTime now, String feedback) {
        this.createDate = now;
        this.feedback = feedback;
    }
}
