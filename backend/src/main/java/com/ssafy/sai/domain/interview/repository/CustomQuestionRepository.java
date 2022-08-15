package com.ssafy.sai.domain.interview.repository;
import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomQuestionRepository extends JpaRepository<CustomInterviewQuestion, Long> {

    Optional<CustomInterviewQuestion> findById(Long id);

    List<CustomInterviewQuestion> findQuestionsByMemberId(Long id);

}