package com.ssafy.sai.db.repository;

import com.ssafy.sai.db.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
