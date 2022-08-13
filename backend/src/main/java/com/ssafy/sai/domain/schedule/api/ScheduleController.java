package com.ssafy.sai.domain.schedule.api;


import com.ssafy.sai.domain.schedule.dto.request.ScheduleCreateRequest;
import com.ssafy.sai.domain.schedule.service.ScheduleService;
import com.ssafy.sai.global.common.DataResponse;
import com.ssafy.sai.global.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;


    /**
     * @메소드 일정 전체 조회 api
     * @param member_id 조회할 교육생의 PK
     * @return 조회한 교육생의 전체 일정
     */
    @GetMapping("/{member_id}")
    public ResponseEntity<? extends DataResponse> getScheduleList(@PathVariable Long member_id){
        return ResponseEntity.ok().body(new DataResponse(scheduleService.selectAll(member_id)));
    }


    /**
     * @메소드 일정 추가 api
     * @param member_id 교육생의 PK
     * @param request (오늘 날짜 (yyyy-MM-dd), 일정 시작 시간 (HH:mm), 일정 종료 시간 (HH:mm) , 분류, 상세정보, 교육생 PK, 컨설턴트 PK)
     * @return 응답 코드와 메세지
     */
    @PostMapping("/{member_id}")
    public ResponseEntity createSchedule(@PathVariable Long member_id, @RequestBody ScheduleCreateRequest request){
        // request 안에 id 값 안받아도 될듯.
        scheduleService.insert(member_id, request);
        return ResponseEntity.ok().body(new MessageResponse<>());

    }

    /**
     * @메소드 일정 삭제 api
     * @param member_id 교육생의 PK
     * @param schedule_id 삭제할 일정의 PK
     * @return 응답 코드와 메세지
     */
    @DeleteMapping("/{member_id}/{schedule_id}")
    public ResponseEntity deleteSchedule(@PathVariable Long member_id, @PathVariable Long schedule_id){
        scheduleService.remove(member_id, schedule_id);
        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    /**
     * @메소드 담당 컨설턴트 조회
     * @param member_id 교육생의 PK
     * @return 조회한 컨설턴트 id, 이름
     */
    @GetMapping("/{member_id}/consultant")
    public ResponseEntity<? extends DataResponse> getConsultantList(@PathVariable Long member_id){
        return ResponseEntity.ok().body(new DataResponse(scheduleService.selectConsultants(member_id)));
    }

    /**
     * @메소드 현재 시간 기준으로 앞으로 다가올 일정 조회 api (최대 5개)
     * @param member_id 멤버의 PK
     * @return 일정 정보 + 멤버 PK, 멤버 이름
     */
    @GetMapping("/{member_id}/recent")
    public ResponseEntity<? extends DataResponse> getScheduleSinceToday(@PathVariable Long member_id){
        return ResponseEntity.ok().body(new DataResponse(scheduleService.selectScheduleSinceToday(member_id)));
    }


    /**
     * @메소드 특정 멤버의 특정 날짜 일정을 조회하는 api
     * @param member_id 멤버의 PK
     * @param date 특정 날짜 (yyyy-MM-dd)
     * @return 특정 날짜의 일정 정보
     */
    @GetMapping("/{member_id}/{date}")
    public ResponseEntity<? extends DataResponse> selectScheduleOnSpecificDay(@PathVariable Long member_id, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date){
        return ResponseEntity.ok().body(new DataResponse(scheduleService.selectScheduleDay(member_id, date)));
    }

}
