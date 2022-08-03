package com.ssafy.sai.domain.schedule.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleSinceTodayByStudentResponse {

    private Long id;

    private LocalDate scheduleDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String category;

    private String detail;

    private Long consultantId;
    private String consultantName;

    public ScheduleSinceTodayByStudentResponse(Long id, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String category, String detail, Long consultantId, String consultantName) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.detail = detail;
        this.consultantId = consultantId;
        this.consultantName = consultantName;
    }
}
