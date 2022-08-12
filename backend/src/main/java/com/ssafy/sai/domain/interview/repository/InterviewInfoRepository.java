package com.ssafy.sai.domain.interview.repository;

import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewInfoRepository extends JpaRepository<InterviewInfo, Long> {

}
