package com.ssafy.sai.domain.interview.repository;
import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomQuestionRepository extends JpaRepository<CustomInterviewQuestion, Long> {

    List<CustomInterviewQuestion> findByMember(Member member);

    List<CustomInterviewQuestion> findQuestionsByMemberId(Long id);

    List<CustomInterviewQuestion> findAllByMember(Member member);


}