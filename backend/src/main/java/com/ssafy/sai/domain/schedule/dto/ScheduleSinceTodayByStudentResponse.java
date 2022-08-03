package com.ssafy.sai.domain.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleSinceTodayByStudentResponse {

    private Long id;

    private LocalDate scheduleDate;

    @JsonFormat(pattern = "hh:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "hh:mm")
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
