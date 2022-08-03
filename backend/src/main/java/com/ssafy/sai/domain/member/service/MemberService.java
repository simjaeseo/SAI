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
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.repository.*;
import com.ssafy.sai.global.config.SpringSecurityConfig;
import com.ssafy.sai.global.util.auth.CustomUserDetailService;
import com.ssafy.sai.global.util.auth.CustomUserDetails;
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

    public MemberDto findMemberOne(Long memberId) {
        Member findMember = memberRepository.findMemberEntityGraph(memberId);
        return new MemberDto(findMember);
    }

    @Transactional
    public void updateMember(Long id, MemberUpdateRequest request) throws Exception {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberExceptionType.WRONG_MEMBER_INFORMATION));

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
    }

    public boolean updatedPassword(@AuthenticationPrincipal CustomUserDetails customUserDetails, PasswordDto passwordDto) {
        try {
            // 현재 접속중인 회원의 이메일과 일치하는 회원을 찾는다
            System.out.println(customUserDetails.getId());
            Member member = memberRepository.findMemberById(customUserDetails.getId());
            BCryptPasswordEncoder bCryptPasswordEncoder = security.bCryptPasswordEncoder();

            // 입력한 현재 비밀번호와 접속중인 회원의 비밀번호가 일치하고,
            if (bCryptPasswordEncoder.matches(passwordDto.getPassword(), member.getPassword())) {
                // 새로운 비밀번호와 비밀번호 확인 값이 같으면
                if (passwordDto.getNewPassword().equals(passwordDto.getNewPasswordCheck())) {
                    // 새로운 비밀번호 값을 암호화한뒤
                    String hashPassword = bCryptPasswordEncoder.encode(passwordDto.getNewPasswordCheck());

                    // native 쿼리로 회원 비밀번호 업데이트
                    memberRepository.updatePassword(hashPassword, member.getEmail());
                    // 접속중인 회원 비밀번호 변경
                    customUserDetails.setPassword(hashPassword);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}