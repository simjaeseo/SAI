package com.ssafy.sai.domain.member.repository;

import com.ssafy.sai.domain.job.domain.InterestedJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestedJobRepository extends JpaRepository<InterestedJob, Long> {
}
