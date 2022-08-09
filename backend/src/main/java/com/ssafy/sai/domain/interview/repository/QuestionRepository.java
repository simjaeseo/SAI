package com.ssafy.sai.domain.interview.repository;
import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<InterviewQuestion, Long> {
    List<InterviewQuestion> findAllByQuestionTypeAndQuestionDetailType(String questionType,String questionDetailType);
    Optional<InterviewQuestion> findById(Long id);


}
