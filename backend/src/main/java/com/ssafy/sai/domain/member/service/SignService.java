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
import com.ssafy.sai.domain.member.dto.request.ConsultantSignUpRequest;
import com.ssafy.sai.domain.member.dto.request.MemberLoginRequest;
import com.ssafy.sai.domain.member.dto.request.MemberSignUpRequest;
import com.ssafy.sai.domain.member.dto.response.AuthenticationConDto;
import com.ssafy.sai.domain.member.dto.response.AuthenticationDto;
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

    @Transactional
    public AuthenticationDto signUpMember(MemberSignUpRequest request) {

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

        // 데이터 저장
        Member findMember = memberRepository.save(memberDto);

        // 관심기업 저장
        for (EnterpriseName enterpriseName : request.getInterestedEnterprises()) {
            Enterprise enterprise = enterpriseRepository.findEnterpriseByName(enterpriseName.getName());

            InterestedEnterpriseCreateRequest interestedEnterpriseCreateRequest = new InterestedEnterpriseCreateRequest(enterprise, findMember);
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
        Optional<Campus> campus = campusRepository.findByCityAndClassNumber(request.getCampus().getCity(), request.getCampus().getClassNumber());
        findMember.updateCampus(campus.get());

        return modelMapper.map(findMember, AuthenticationDto.class);
    }

    @Transactional
    public AuthenticationConDto signUpConsultant(ConsultantSignUpRequest request) {

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
        Optional<Campus> campus = campusRepository.findByCityAndClassNumber(request.getCampus().getCity(), 0);
        findMember.updateCampus(campus.get());

        return modelMapper.map(findMember, AuthenticationConDto.class);
    }

    @Transactional
    public AuthenticationDto loginMember(MemberLoginRequest request) {

        // Member -> DTO
        Member memberDto = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        // Email 유효한지 체크
        Member member = memberRepository.findByEmail(memberDto.getEmail())
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        // 비밀번호 일치 체크
        if (!passwordEncoder.matches(memberDto.getPassword(), member.getPassword())) {
            throw new MemberException(MemberExceptionType.WRONG_PASSWORD);
        }

        return modelMapper.map(member, AuthenticationDto.class);
    }

}