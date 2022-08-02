package com.ssafy.sai.domain.member.repository;

import com.ssafy.sai.domain.member.domain.Campus;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Member findMemberById(Long id);

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

    @Modifying
    @Transactional
    @Query(value = "update member m set m.password=?1 where m.email=?2", nativeQuery = true)
    void updatePassword(String newPasswordCheck, String email);

    Member findMemberByEmail(String email);

    Member findMemberById(long id);
}
