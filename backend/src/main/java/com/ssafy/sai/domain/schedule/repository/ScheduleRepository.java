package com.ssafy.sai.domain.schedule.repository;

import com.ssafy.sai.domain.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


}
