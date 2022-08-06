package com.ssafy.sai.domain.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.sai.domain.member.domain.Member;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleAllByStudentResponse {

    private Long id;

    private LocalDate scheduleDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private String category;

    private String detail;

    private String consultantName;

    public ScheduleAllByStudentResponse(Long id, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String category, String detail, String consultantName) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.detail = detail;
        this.consultantName = consultantName;
    }
}
