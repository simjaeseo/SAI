package com.ssafy.sai.domain.member.service;

import com.ssafy.sai.domain.job.dto.InterestedEnterpriseCreateRequest;
import com.ssafy.sai.domain.job.dto.InterestedJobCreateRequest;
import com.ssafy.sai.domain.job.repository.EnterpriseRepository;
import com.ssafy.sai.domain.job.repository.InterestedEnterpriseRepository;
import com.ssafy.sai.domain.job.repository.InterestedJobRepository;
import com.ssafy.sai.domain.job.repository.JobRepository;
import com.ssafy.sai.domain.member.domain.Campus;
import com.ssafy.sai.domain.member.dto.*;
import com.ssafy.sai.domain.job.dto.EnterpriseName;
import com.ssafy.sai.domain.job.dto.JobName;
import com.ssafy.sai.domain.job.domain.Enterprise;
import com.ssafy.sai.domain.job.domain.Job;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.dto.request.MemberUpdateRequest;
import com.ssafy.sai.domain.member.dto.response.MemberResponse;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.repository.*;
import com.ssafy.sai.global.config.SpringSecurityConfig;
import com.ssafy.sai.global.util.auth.CustomUserDetails;
import com.ssafy.sai.global.util.validation.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final InterestedEnterpriseRepository interestedEnterpriseRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final JobRepository jobRepository;
    private final InterestedJobRepository interestedJobRepository;
    private final CampusRepository campusRepository;
    private final SpringSecurityConfig security;

    /**
     * @메소드 교육생 정보 조회 서비스
     * @param id 회원 PK
     * @return 조회한 교육생의 정보 DTO
     * @throws Exception PK 값이 일치하지 않는 경우 예외 발생
     */
    public MemberDto findMemberOne(Long id) throws Exception {
        memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
        Member findMember = memberRepository.findMemberEntityGraph(id);
        return new MemberDto(findMember);
    }

    /**
     * @메소드 컨설턴트 정보 조회 서비스
     * @param id 회원 PK
     * @return 조회한 컨설턴트 정보 DTO
     * @throws Exception PK 값이 일치하지 않는 경우 예외 발생
     */
    public ConsultantDto findConsultantOne(Long id) {
        memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
        Member findMember = memberRepository.findMemberById(id);
        return new ConsultantDto(findMember);
    }

    /**
     * @메소드 교육생 정보 수정 서비스
     * @param id      회원 PK
     * @param request 변경할 교육 정보 폼 DTO
     * @return 수정된 교육생의 정보 DTO
     * @throws Exception 회원 PK를 찾을 수 없는 경우, 이미 존재하는 휴대전화 번호로 변경하는 경우 예외 발생
     */
    @Transactional
    public MemberResponse updateMember(Long id, MemberUpdateRequest request) throws Exception {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberExceptionType.WRONG_MEMBER_INFORMATION));

        if (!Empty.validation(memberRepository.countByPhone(request.getPhone()))) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_PHONE);
        }

        findMember.updateMember(request);
        Optional<Campus> campus = campusRepository.findByCityAndClassNumber(request.getCampus().getCity(), request.getCampus().getClassNumber());
        findMember.updateCampus(campus.get());

        interestedJobRepository.deleteByMemberId(findMember.getId());
        for (JobName jobName : request.getInterestedJobs()) {
            Job job = jobRepository.findJobByName(jobName.getName());
            InterestedJobCreateRequest interestedJobCreateRequest = new InterestedJobCreateRequest(job, findMember);
            interestedJobRepository.save(interestedJobCreateRequest.toEntity());
        }

        interestedEnterpriseRepository.deleteByMemberId(findMember.getId());
        for (EnterpriseName enterpriseName : request.getInterestedEnterprises()) {
            Enterprise enterprise = enterpriseRepository.findEnterpriseByName(enterpriseName.getName());
            InterestedEnterpriseCreateRequest interestedEnterpriseCreateRequest = new InterestedEnterpriseCreateRequest(enterprise, findMember);
            interestedEnterpriseRepository.save(interestedEnterpriseCreateRequest.toEntity());
        }

        return new MemberResponse(findMember);
    }

    /**
     * @메소드 회원 비밀번호 변경 서비스
     * @param passwordDto 비밀번호 변경 폼 양식 (기존 비밀번호, 새로운 비밀번호, 비밀번호 확인)
     * @return 비밀번호가 일치하면 비밀번호 암호화 후 회원 정보 DTO 반환
     * @return 비밀번호가 일치하지 않으면 null 반환
     * @throws Exception 접속중인 회원과 이메일이 일치하지 않으면 예외 발생
     */
    @Transactional
    public MemberResponse updatePassword(PasswordDto passwordDto) {
        Member findMember = memberRepository.findById(passwordDto.getId())
                .orElseThrow(() -> new MemberException(MemberExceptionType.WRONG_MEMBER_INFORMATION));
        BCryptPasswordEncoder bCryptPasswordEncoder = security.bCryptPasswordEncoder();

        if (bCryptPasswordEncoder.matches(passwordDto.getPassword(), findMember.getPassword())) {
            if (passwordDto.getNewPassword().equals(passwordDto.getNewPasswordCheck())) {
                String hashPassword = bCryptPasswordEncoder.encode(passwordDto.getNewPasswordCheck());
                memberRepository.updatePassword(hashPassword, findMember.getEmail());
                return new MemberResponse(findMember);
            }
        } else {
            throw new MemberException(MemberExceptionType.WRONG_PASSWORD);
        }

        return null;
    }
}