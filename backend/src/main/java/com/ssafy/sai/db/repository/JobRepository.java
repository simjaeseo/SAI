package com.ssafy.sai.db.repository;

import com.ssafy.sai.db.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
