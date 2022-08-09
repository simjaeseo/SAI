package com.ssafy.sai.domain.member.service;

import com.ssafy.sai.domain.job.domain.Enterprise;
import com.ssafy.sai.domain.job.domain.InterestedEnterprise;
import com.ssafy.sai.domain.job.domain.InterestedJob;
import com.ssafy.sai.domain.job.domain.Job;
import com.ssafy.sai.domain.job.dto.EnterpriseName;
import com.ssafy.sai.domain.job.dto.InterestedEnterpriseCreateRequest;
import com.ssafy.sai.domain.job.dto.InterestedJobCreateRequest;
import com.ssafy.sai.domain.job.dto.JobName;
import com.ssafy.sai.domain.job.repository.EnterpriseRepository;
import com.ssafy.sai.domain.job.repository.InterestedEnterpriseRepository;
import com.ssafy.sai.domain.job.repository.InterestedJobRepository;
import com.ssafy.sai.domain.job.repository.JobRepository;
import com.ssafy.sai.domain.member.domain.Campus;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.dto.AuthenticationMember;
import com.ssafy.sai.domain.member.dto.ConsultantDto;
import com.ssafy.sai.domain.member.dto.request.ConsultantSignUpRequest;
import com.ssafy.sai.domain.member.dto.request.MemberLoginRequest;
import com.ssafy.sai.domain.member.dto.request.MemberSignUpRequest;
import com.ssafy.sai.domain.member.dto.response.MemberResponse;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.repository.CampusRepository;
import com.ssafy.sai.domain.member.repository.MemberRepository;
import com.ssafy.sai.global.util.validation.Empty;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;
    private final InterestedEnterpriseRepository interestedEnterpriseRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final JobRepository jobRepository;
    private final InterestedJobRepository interestedJobRepository;
    private final CampusRepository campusRepository;

    /**
     * @메소드 교육생 회원가입 서비스
     * @param request 교육생 회원가입 폼 양식
     * @return 교육생 정보 DTO
     * @throws Exception 이미 존재하는 아이디인 경우, 연락처가 중복인 경우 예외 발생
     */
    @Transactional
    public MemberResponse signUpMember(MemberSignUpRequest request) throws MemberException {

        // 아이디 중복체크
        if (!Empty.validation(memberRepository.countByEmail(request.getEmail()))) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_EMAIL);
        }

        // 연락처 중복체크
        if (!Empty.validation(memberRepository.countByPhone(request.getPhone()))) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_PHONE);
        }

        // 비밀번호 암호화
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        // Member -> DTO
        Member memberDto = Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .name(request.getName())
                .birthday(request.getBirthday())
                .memberStatus(request.getMemberStatus())
                .year(request.getYear())
                .phone(request.getPhone())
                .build();

        Member findMember = memberRepository.save(memberDto);

        // 관심기업 저장
        for (EnterpriseName enterpriseName : request.getInterestedEnterprises()) {
            Enterprise enterprise = enterpriseRepository.findEnterpriseByName(enterpriseName.getName());
            InterestedEnterpriseCreateRequest interestedEnterpriseCreateRequest = new InterestedEnterpriseCreateRequest(enterprise, memberDto);
            InterestedEnterprise interestedEnterprise = interestedEnterpriseCreateRequest.toEntity();
            interestedEnterpriseRepository.save(interestedEnterprise);
        }

        // 관심직무 저장
        for (JobName jobName : request.getInterestedJobs()) {
            Job job = jobRepository.findJobByName(jobName.getName());
            InterestedJobCreateRequest interestedJobCreateRequest = new InterestedJobCreateRequest(job, findMember);
            InterestedJob interestedJob = interestedJobCreateRequest.toEntity();
            interestedJobRepository.save(interestedJob);
        }

        // 캠퍼스 정보 저장
        Campus campus = campusRepository.findByCityAndClassNumber(request.getCampus().getCity(), request.getCampus().getClassNumber())
                .orElseThrow(() -> new MemberException(MemberExceptionType.WRONG_MEMBER_INFORMATION));
        findMember.updateCampus(campus);

        return new MemberResponse(findMember);
    }

    /**
     * @메소드 컨설턴트 회원가입 서비스
     * @param request 컨설턴트 회원가입 폼 양식
     * @return 컨설턴트 정보 DTO
     * @throws Exception 이미 존재하는 아이디인 경우, 연락처가 중복인 경우 예외 발생
     */
    @Transactional
    public ConsultantDto signUpConsultant(ConsultantSignUpRequest request) throws MemberException {

        // 아이디 중복체크
        if (!Empty.validation(memberRepository.countByEmail(request.getEmail()))) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_EMAIL);
        }
        // 연락처 중복체크
        if (!Empty.validation(memberRepository.countByPhone(request.getPhone()))) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_PHONE);
        }
        // 비밀번호 암호화
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        // Member -> DTO
        Member memberDto = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .birthday(request.getBirthday())
                .memberStatus(request.getMemberStatus())
                .phone(request.getPhone())
                .build();

        // 데이터 저장
        Member findMember = memberRepository.save(memberDto);

        // 캠퍼스 정보 저장
        Campus campus = campusRepository.findByCityAndClassNumber(request.getCampus().getCity(), null)
                .orElseThrow(() -> new MemberException(MemberExceptionType.WRONG_MEMBER_INFORMATION));
        findMember.updateCampus(campus);

        return new ConsultantDto(findMember);
    }

    /**
     * @메소드 회원 로그인 서비스
     * @param request 로그인 폼 양식(이메일, 비밀번호)
     * @return 로그인 회원 DTO
     * @throws Exception 이메일이 일치하지 않는 경우, 비밀번호가 일치하지 않는 경우 예외 발생
     */
    @Transactional
    public AuthenticationMember loginMember(MemberLoginRequest request) throws MemberException {

        // Member -> DTO
        Member memberDto = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        // Email 유효한지 체크
        Member findMember = memberRepository.findByEmail(memberDto.getEmail())
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        // 비밀번호 일치 체크
        if (!passwordEncoder.matches(memberDto.getPassword(), findMember.getPassword())) {
            throw new MemberException(MemberExceptionType.WRONG_PASSWORD);
        }

        return modelMapper.map(findMember, AuthenticationMember.class);
    }

}