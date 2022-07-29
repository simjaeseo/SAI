package com.ssafy.sai.api.service;

import com.ssafy.sai.api.request.MemberSignUpRequest;
import com.ssafy.sai.api.request.MemberUpdateRequest;
import com.ssafy.sai.db.entity.Member;
import com.ssafy.sai.db.repository.MemberRepository;
import com.ssafy.sai.exception.member.MemberException;
import com.ssafy.sai.exception.member.MemberExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    회원 가입
     */
    @Transactional
    public void signUpMember(MemberSignUpRequest request) throws Exception {
        Member member = request.toEntity();

        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_EMAIL);
        }

        memberRepository.save(member);
    }

    @Transactional
    public void updateMember(Long id, MemberUpdateRequest request) throws Exception {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.WRONG_MEMBER_INFORMATION));

        findMember.updateMember(request);
    }
}