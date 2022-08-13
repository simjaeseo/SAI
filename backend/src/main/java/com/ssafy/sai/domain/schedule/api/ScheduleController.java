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
public class    ScheduleController {

    private final ScheduleService scheduleService;


    /**
     * @param memberId 조회할 교육생의 PK
     * @return 조회한 교육생의 전체 일정
     * @메소드 일정 전체 조회 api
     */
    @GetMapping("/{member-id}")
    public ResponseEntity<? extends DataResponse> getScheduleList(@PathVariable("member-id") Long memberId) {
        return ResponseEntity.ok().body(new DataResponse(scheduleService.selectAll(memberId)));
    }


    /**
     * @param memberId 교육생의 PK
     * @param request   (오늘 날짜 (yyyy-MM-dd), 일정 시작 시간 (HH:mm), 일정 종료 시간 (HH:mm) , 분류, 상세정보, 교육생 PK, 컨설턴트 PK)
     * @return 응답 코드와 메세지
     * @메소드 일정 추가 api
     */
    @PostMapping("/{member-id}")
    public ResponseEntity createSchedule(@PathVariable("member-id") Long memberId, @RequestBody ScheduleCreateRequest request) {
        // request 안에 id 값 안받아도 될듯.
        scheduleService.insert(memberId, request);
        return ResponseEntity.ok().body(new MessageResponse<>());

    }

    /**
     * @param memberId   교육생의 PK
     * @param scheduleId 삭제할 일정의 PK
     * @return 응답 코드와 메세지
     * @메소드 일정 삭제 api
     */
    @DeleteMapping("/{member-id}/{schedule-id}")
    public ResponseEntity deleteSchedule(@PathVariable("member-id") Long memberId, @PathVariable("schedule-id") Long scheduleId) {
        scheduleService.remove(memberId, scheduleId);
        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    /**
     * @param memberId 교육생의 PK
     * @return 조회한 컨설턴트 id, 이름
     * @메소드 담당 컨설턴트 조회
     */
    @GetMapping("/{member-id}/consultant")
    public ResponseEntity<? extends DataResponse> getConsultantList(@PathVariable("member-id") Long memberId) {
        return ResponseEntity.ok().body(new DataResponse(scheduleService.selectConsultants(memberId)));
    }

    /**
     * @param memberId 멤버의 PK
     * @return 일정 정보 + 멤버 PK, 멤버 이름
     * @메소드 현재 시간 기준으로 앞으로 다가올 일정 조회 api (최대 5개)
     */
    @GetMapping("/{member-id}/recent")
    public ResponseEntity<? extends DataResponse> getScheduleSinceToday(@PathVariable("member-id") Long memberId) {
        return ResponseEntity.ok().body(new DataResponse(scheduleService.selectScheduleSinceToday(memberId)));
    }


    /**
     * @param memberId 멤버의 PK
     * @param date      특정 날짜 (yyyy-MM-dd)
     * @return 특정 날짜의 일정 정보
     * @메소드 특정 멤버의 특정 날짜 일정을 조회하는 api
     */
    @GetMapping("/{member-id}/{date}")
    public ResponseEntity<? extends DataResponse> selectScheduleOnSpecificDay(@PathVariable("member-id") Long memberId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok().body(new DataResponse(scheduleService.selectScheduleDay(memberId, date)));
    }

}
