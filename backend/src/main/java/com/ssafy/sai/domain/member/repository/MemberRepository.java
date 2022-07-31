package com.ssafy.sai.domain.member.repository;

import com.ssafy.sai.domain.member.domain.Campus;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("select m, c.city, c.classNumber from Member m left join m.campus c where m.id = :id")
    Member findMemberById(@Param("id") Long id);

    List<Member> findByCampusAndMemberStatus(Campus campus, MemberStatus memberStatus);
}
