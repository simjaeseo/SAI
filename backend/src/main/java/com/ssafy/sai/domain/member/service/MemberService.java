package com.ssafy.sai.domain.member.service;

import com.ssafy.sai.domain.job.dto.InterestedEnterpriseCreateRequest;
import com.ssafy.sai.domain.job.dto.InterestedJobCreateRequest;
import com.ssafy.sai.domain.member.dto.MemberSignUpRequest;
import com.ssafy.sai.domain.member.dto.MemberUpdateRequest;
import com.ssafy.sai.domain.job.dto.EnterpriseId;
import com.ssafy.sai.domain.job.dto.JobId;
import com.ssafy.sai.domain.job.domain.Enterprise;
import com.ssafy.sai.domain.job.domain.InterestedEnterprise;
import com.ssafy.sai.domain.job.domain.InterestedJob;
import com.ssafy.sai.domain.job.domain.Job;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final InterestedEnterpriseRepository interestedEnterpriseRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final JobRepository jobRepository;
    private final InterestedJobRepository interestedJobRepository;

    /*
    회원 가입
     */
    @Transactional
    public void signUpMember(MemberSignUpRequest request) throws Exception {
        Member member = request.toEntity();

        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_EMAIL);
        }

        Member findMember = memberRepository.saveAndFlush(member);
        for (EnterpriseId enterpriseId : request.getInterestedEnterprises()) {
            Optional<Enterprise> enterprise = enterpriseRepository.findById(enterpriseId.getId());

            // enterprise.get() 인자 값 수정
            InterestedEnterpriseCreateRequest interestedEnterpriseCreateRequest = new InterestedEnterpriseCreateRequest(enterprise.get(), findMember);
            InterestedEnterprise interestedEnterprise = interestedEnterpriseCreateRequest.toEntity();
            interestedEnterpriseRepository.save(interestedEnterprise);
        }

        for (JobId jobId : request.getInterestedJobs()) {
            Optional<Job> job = jobRepository.findById(jobId.getId());
            InterestedJobCreateRequest interestedJobCreateRequest = new InterestedJobCreateRequest(job.get(), findMember);
            InterestedJob interestedJob = interestedJobCreateRequest.toEntity();
            interestedJobRepository.save(interestedJob);
        }
    }

    @Transactional
    public void updateMember(Long id, MemberUpdateRequest request) throws Exception {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.WRONG_MEMBER_INFORMATION));

        findMember.updateMember(request);
    }
}