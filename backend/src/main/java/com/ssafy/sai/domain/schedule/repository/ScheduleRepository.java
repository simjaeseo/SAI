package com.ssafy.sai.domain.schedule.repository;

import com.ssafy.sai.domain.schedule.domain.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 교육생 전체 일정 조회
    @Query(value = "select s from Schedule s" +
            " join fetch s.memberStudent" +
            " where s.memberStudent.id = :memberId",
            countQuery = "select count(s) from Schedule s")
    Page<Schedule> selectAllByStudent(@Param("memberId") Long memberId, Pageable pageable);


    // 컨설턴트 전체 일정 조회
    @Query(value = "select s from Schedule s" +
            " join fetch s.memberConsultant" +
            " where s.memberConsultant.id = :memberId",
            countQuery = "select count(s) from Schedule s")
    Page<Schedule> selectAllByConsultant(@Param("memberId") Long memberId, Pageable pageable);


    // 교육생 최근 일정 조회
    @Query(value = "select s from Schedule s" +
            " join fetch s.memberStudent" +
            " where s.memberStudent.id = :memberId and (s.scheduleDate = :today and s.startTime >= :currentTime) or s.scheduleDate > :today",
            countQuery = "select count(s) from Schedule s")
    Page<Schedule> selectScheduleSinceTodayByStudent(@Param("memberId") Long memberId, @Param("today") LocalDate today, @Param("currentTime") LocalTime currentTime, Pageable pageable);

    // 컨설턴트 최근 일정 조회
    @Query(value = "select s from Schedule s" +
            " join fetch s.memberConsultant" +
            " where s.memberConsultant.id = :memberId and (s.scheduleDate = :today and s.startTime >= :currentTime) or s.scheduleDate > :today",
            countQuery = "select count(s) from Schedule s")
    Page<Schedule> selectScheduleSinceTodayByConsultant(@Param("memberId") Long memberId, @Param("today") LocalDate today, @Param("currentTime") LocalTime currentTime, Pageable pageable);


    // 교육생 특정 날짜 일정 조회
    @Query("select s from Schedule s" +
            " where s.memberStudent.id = :memberId and s.scheduleDate = :date" +
            " order by s.scheduleDate asc, s.startTime asc")
    List<Schedule> selectScheduleOnSpecificDayByStudent(@Param("memberId") Long id, @Param("date") LocalDate date);

    // 컨설턴트 특정 날짜 일정 조회
    @Query("select s from Schedule s" +
            " where s.memberConsultant.id = :memberId and s.scheduleDate = :date" +
            " order by s.scheduleDate asc, s.startTime asc")
    List<Schedule> selectScheduleOnSpecificDayByConsultant(@Param("memberId") Long id, @Param("date") LocalDate date);
}
