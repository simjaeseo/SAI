package com.ssafy.sai.domain.schedule.dto.request;

import com.ssafy.sai.domain.schedule.domain.Schedule;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ScheduleCreateRequest {

    @NotNull
    // datetime 형식 지정하기
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduleDate;

    @NotNull
    // LocalTime 형식 지정하기
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @NotNull
    // LocalTime 형식 지정하기
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private String category;

    @NotNull
    private String detail;

    private Long memberStudentId;

    private Long memberConsultantId;

    public Schedule toEntity(){
        return Schedule.builder()
                .scheduleDate(scheduleDate)
                .startTime(startTime)
                .endTime(endTime)
                .category(category)
                .detail(detail)
                .build();
    }

}
