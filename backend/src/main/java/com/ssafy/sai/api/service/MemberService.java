package com.ssafy.sai.api.service;

import com.ssafy.sai.api.request.InterestedEnterpriseCreateRequest;
import com.ssafy.sai.api.request.InterestedJobCreateRequest;
import com.ssafy.sai.api.request.MemberSignUpRequest;
import com.ssafy.sai.api.request.MemberUpdateRequest;
import com.ssafy.sai.db.dto.EnterpriseId;
import com.ssafy.sai.db.dto.JobId;
import com.ssafy.sai.db.entity.*;
import com.ssafy.sai.db.repository.*;
import com.ssafy.sai.exception.member.MemberException;
import com.ssafy.sai.exception.member.MemberExceptionType;
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