package com.ssafy.sai.domain.schedule.repository;

import com.ssafy.sai.domain.schedule.domain.Schedule;
import com.ssafy.sai.domain.schedule.dto.response.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 교육생 전체 일정 조회
    @Query("select new com.ssafy.sai.domain.schedule.dto.ScheduleAllByStudentResponse(s.id, s.scheduleDate, s.startTime, s.endTime, s.category, s.detail, m.name) from Schedule as s left outer join Member as m on s.memberConsultant.id = m.id where s.memberStudent.id = :memberId order by s.scheduleDate asc, s.startTime asc")
    List<ScheduleAllByStudentResponse> selectAllByStudent(@Param("memberId") Long memberId);


    // 컨설턴트 전체 일정 조회
    @Query("select new com.ssafy.sai.domain.schedule.dto.ScheduleAllByConsultantResponse(s.id, s.scheduleDate, s.startTime, s.endTime, s.category, s.detail, m.name) from Schedule as s left outer join Member as m on s.memberStudent.id = m.id where s.memberConsultant.id = :memberId order by s.scheduleDate asc, s.startTime asc")
    List<ScheduleAllByConsultantResponse> selectAllByConsultant(@Param("memberId") Long memberId);


    // 교육생 최근 일정 조회
    @Query(value = "select new com.ssafy.sai.domain.schedule.dto.ScheduleSinceTodayByStudentResponse(s.id, s.scheduleDate, s.startTime, s.endTime, s.category, s.detail, m.id, m.name) from Schedule as s left outer join Member as m on s.memberConsultant.id = m.id where s.memberStudent.id = :memberId and (s.scheduleDate = :today and s.startTime >= :currentTime) or s.scheduleDate > :today order by s.scheduleDate asc, s.startTime asc")
    List<ScheduleSinceTodayByStudentResponse> selectScheduleSinceTodayByStudent(@Param("memberId") Long memberId, @Param("today")LocalDate today, @Param("currentTime") LocalTime currentTime, Pageable pageable);

    // 컨설턴트 최근 일정 조회
    @Query(value = "select new com.ssafy.sai.domain.schedule.dto.ScheduleSinceTodayByConsultantResponse(s.id, s.scheduleDate, s.startTime, s.endTime, s.category, s.detail, m.id, m.name) from Schedule as s left outer join Member as m on s.memberStudent.id = m.id where s.memberConsultant.id = :memberId and (s.scheduleDate = :today and s.startTime >= :currentTime) or s.scheduleDate > :today order by s.scheduleDate asc, s.startTime asc")
    List<ScheduleSinceTodayByConsultantResponse> selectScheduleSinceTodayByConsultant(@Param("memberId") Long memberId, @Param("today")LocalDate today, @Param("currentTime") LocalTime currentTime, Pageable pageable);


    // 교육생 특정 날짜 일정 조회
    @Query("select new com.ssafy.sai.domain.schedule.dto.ScheduleOnSpecificDayByStudentResponse(s.id, s.scheduleDate, s.startTime) from Schedule as s where s.memberStudent.id = :memberId and s.scheduleDate = :date order by s.scheduleDate asc, s.startTime asc")
    List<ScheduleOnSpecificDayByStudentResponse> selectScheduleOnSpecificDayByStudent(@Param("memberId") Long id, @Param("date") LocalDate date);

    // 컨설턴트 특정 날짜 일정 조회
    @Query("select new com.ssafy.sai.domain.schedule.dto.ScheduleOnSpecificDayByConsultantResponse(s.id, s.scheduleDate, s.startTime) from Schedule as s where s.memberConsultant.id = :memberId and s.scheduleDate = :date order by s.scheduleDate asc, s.startTime asc")
    List<ScheduleOnSpecificDayByConsultantResponse> selectScheduleOnSpecificDayByConsultant(@Param("memberId") Long id, @Param("date") LocalDate date);



}
