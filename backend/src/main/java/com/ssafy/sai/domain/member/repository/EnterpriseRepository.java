package com.ssafy.sai.domain.member.repository;

import com.ssafy.sai.domain.job.domain.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
