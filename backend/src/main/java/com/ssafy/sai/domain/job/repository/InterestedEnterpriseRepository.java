package com.ssafy.sai.domain.job.repository;

import com.ssafy.sai.domain.job.domain.InterestedEnterprise;
import com.ssafy.sai.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterestedEnterpriseRepository extends JpaRepository<InterestedEnterprise, Long> {

    void deleteByMemberId(Long memberId);
}
