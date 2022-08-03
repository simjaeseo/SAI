package com.ssafy.sai.domain.schedule.api;


import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.dto.ConsultantAllByCampusResponse;
import com.ssafy.sai.domain.schedule.domain.Schedule;
import com.ssafy.sai.domain.schedule.dto.ScheduleAllByStudentResponse;
import com.ssafy.sai.domain.schedule.dto.ScheduleCreateRequest;
import com.ssafy.sai.domain.schedule.dto.ScheduleSinceTodayByStudentResponse;
import com.ssafy.sai.domain.schedule.service.ScheduleService;
import com.ssafy.sai.global.common.DataResponse;
import com.ssafy.sai.global.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;


/*    // 일정 전체 조회
    @GetMapping("/{member_id}")
    public DataResponse<?> getScheduleList(@PathVariable Long member_id){
        return scheduleService.selectAll(member_id);
    }
    
    // 일정 추가
    @PostMapping("/{member_id}")
    public MessageResponse createSchedule(@PathVariable Long member_id, @RequestBody ScheduleCreateRequest request){
        // request 안에 id 값 안받아도 될듯.
        return scheduleService.insert(member_id, request);
    }

    //일정 삭제
    @DeleteMapping("/{member_id}/{schedule_id}")
    public MessageResponse deleteSchedule(@PathVariable Long member_id, @PathVariable Long schedule_id){
        return scheduleService.remove(member_id, schedule_id);
    }

    // 담당 컨설턴트 조회
    @GetMapping("/{member_id}/consultant")
    public DataResponse<List<ConsultantAllByCampusResponse>> getConsultantList(@PathVariable Long member_id){
        return scheduleService.selectConsultants(member_id);
    }

    @GetMapping("/{member_id}/recent")
    public DataResponse<?> getScheduleSinceToday(@PathVariable Long member_id){
        return scheduleService.selectScheduleSinceToday(member_id);
    }*/

    // 여기서 member_id는 일정을 알고싶어하는 멤버의 id
    @GetMapping("/{member_id}/{date}")
    public DataResponse<?> selectScheduleOnSpecificDay(@PathVariable Long member_id, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date){
        System.out.println(member_id);
        System.out.println(date.getClass().getName());
        return scheduleService.selectScheduleDay(member_id, date);
    }

}
