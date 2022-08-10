package com.ssafy.sai.domain.member.repository;

import com.ssafy.sai.domain.member.domain.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Long> {

    @Query("select m.profilePicture from Member m where m.id = :id")
    ProfilePicture findProfilePictureById(@Param("id") Long id);
}
