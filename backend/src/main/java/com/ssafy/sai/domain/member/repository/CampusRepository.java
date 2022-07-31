package com.ssafy.sai.domain.member.repository;

import com.ssafy.sai.domain.member.domain.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampusRepository extends JpaRepository<Campus, Long> {

    Optional<Campus> findByCityAndClassNumber(String city, Integer classNumber);
    Campus findByCity(String city);
}
