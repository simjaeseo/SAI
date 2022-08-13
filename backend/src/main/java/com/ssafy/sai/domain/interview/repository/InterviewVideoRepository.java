package com.ssafy.sai.domain.interview.repository;

import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import com.ssafy.sai.domain.interview.domain.InterviewVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewVideoRepository extends JpaRepository<InterviewVideo, Long> {

    Optional<InterviewVideo> findByInterviewInfo(InterviewInfo interviewInfo);


}
