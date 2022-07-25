package com.ssafy.sai.repository;

import com.ssafy.sai.domain.InterviewRoom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InterviewRepository extends JpaRepository<InterviewRoom, Long> {

}
