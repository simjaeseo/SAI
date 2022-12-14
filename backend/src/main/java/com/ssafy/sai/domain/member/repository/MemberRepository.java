package com.ssafy.sai.domain.member.repository;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import com.ssafy.sai.domain.member.dto.response.ConsultantAllByCampusResponse;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @EntityGraph(attributePaths = {"profilePicture"})
    @Query("select m from Member m where m.id = :id")
    Member findMemberById(@Param("id") Long id);

    @EntityGraph(attributePaths = {"campus", "profilePicture"})
    @Query("select m from Member m where m.id = :id")
    Member findMemberEntityGraph(@Param("id") Long id);

    @Query("select m from Member m" +
            " where m.campus.city = :city and m.memberStatus = :memberStatus")
    List<Member> findConsultantByCampus(@Param("city") String city, @Param("memberStatus") MemberStatus memberStatus);

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
    @Query(value = "update member m set m.password= :newPasswordCheck where m.email= :email", nativeQuery = true)
    void updatePassword(@Param("newPasswordCheck") String newPasswordCheck, @Param("email") String email);

    Member findMemberByNameAndBirthday(String name, LocalDate birthday);

    @Query("select m from Member m" +
            " join fetch m.campus" +
            " where m.name = :name")
    List<Member> findMembersByName(@Param("name") String name);
}
