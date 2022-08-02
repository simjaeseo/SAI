package com.ssafy.sai.domain.schedule.repository;

import com.ssafy.sai.domain.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule as s where s.scheduleDate >= :today order by s.scheduleDate asc")
    List<Schedule> selectScheduleSinceToday(@Param("today")LocalDate today);

}
