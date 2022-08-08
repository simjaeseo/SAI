package com.ssafy.sai.domain.member.repository;

import com.ssafy.sai.domain.member.domain.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Long> {

    Optional<ProfilePicture> findById(Long id);
}
