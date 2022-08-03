package com.ssafy.sai.domain.schedule.repository;

import com.ssafy.sai.domain.schedule.domain.Schedule;
import com.ssafy.sai.domain.schedule.dto.ScheduleAllByConsultantResponse;
import com.ssafy.sai.domain.schedule.dto.ScheduleAllByStudentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select new com.ssafy.sai.domain.schedule.dto.ScheduleAllByStudentResponse(s.id, s.scheduleDate, s.startTime, s.endTime, s.category, s.detail, m.name) from Schedule as s left outer join Member as m on s.memberConsultant.id = m.id where s.memberStudent.id = :memberId order by s.scheduleDate asc, s.startTime asc")
    List<ScheduleAllByStudentResponse> selectAllByStudent(@Param("memberId") Long memberId);

    @Query("select new com.ssafy.sai.domain.schedule.dto.ScheduleAllByConsultantResponse(s.id, s.scheduleDate, s.startTime, s.endTime, s.category, s.detail, m.name) from Schedule as s left outer join Member as m on s.memberStudent.id = m.id where s.memberConsultant.id = :memberId order by s.scheduleDate asc, s.startTime asc")
    List<ScheduleAllByConsultantResponse> selectAllByConsultant(@Param("memberId") Long memberId);


    @Query(value = "select * from Schedule as s where s.schedule_date >= :today order by s.schedule_date asc, s.start_time asc limit 5",nativeQuery = true)
    List<Schedule> selectScheduleSinceToday(@Param("today")LocalDate today);

}
