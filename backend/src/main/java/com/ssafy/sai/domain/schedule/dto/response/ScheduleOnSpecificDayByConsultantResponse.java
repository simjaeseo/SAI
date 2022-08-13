package com.ssafy.sai.domain.schedule.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.sai.domain.schedule.domain.Schedule;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleOnSpecificDayByConsultantResponse {

    private Long id;

    private LocalDate scheduleDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

//    private LocalTime endTime;
//
//    private String category;
//
//    private String detail;
//
//    private String studentName;
//
//    public ScheduleOnSpecificDayByConsultantResponse(Long id, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String category, String detail, String studentName) {
//        this.id = id;
//        this.scheduleDate = scheduleDate;
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.category = category;
//        this.detail = detail;
//        this.studentName = studentName;
//    }


    public ScheduleOnSpecificDayByConsultantResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.scheduleDate = schedule.getScheduleDate();
        this.startTime = schedule.getStartTime();
    }
}
