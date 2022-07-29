package com.ssafy.sai.domain.member.repository;

import com.ssafy.sai.domain.job.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
