package com.ssafy.sai.domain.interview.repository;

import com.ssafy.sai.domain.interview.domain.UsedInterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedInterviewQuestionRepository extends JpaRepository<UsedInterviewQuestion, Long> {
}
