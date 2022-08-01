package com.ssafy.sai.domain.member.repository;

import com.ssafy.sai.domain.member.domain.Campus;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @EntityGraph(attributePaths = {"campus"})
    @Query("select m from Member m where m.id = :id")
    Member findMemberEntityGraph(@Param("id") Long id);

    List<Member> findByCampusAndMemberStatus(Campus campus, MemberStatus memberStatus);

    @Query(value =
            "select count(*) " +
            "from member " +
            "where email = :email ", nativeQuery = true)
    Integer countByEmail(@Param("email") String email);

    @Query(value =
            "select count(*) " +
            "from member " +
            "where phone = :phone ", nativeQuery = true)
    Integer countByPhone(@Param("phone") String phone);

    Optional<Member> findMemberByEmail(String email);
}
