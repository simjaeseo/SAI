package com.ssafy.sai.domain.interview.repository;

import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.interview.domain.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<InterviewQuestion, Long> {
    List<InterviewQuestion> findAllByQuestionType(QuestionType questionType);
}
