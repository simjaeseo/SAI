package com.ssafy.sai.api.service;

import com.ssafy.sai.db.dto.MemberSignUpDto;
import com.ssafy.sai.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /*
    회원 가입
     */
    @Transactional
    public Long signUp(MemberSignUpDto memberSignUpDto) {
        if (memberRepository.existsByEmail(memberSignUpDto.getEmail())) {
            return 0L;
        }

        return memberRepository.save(memberSignUpDto.toEntity(passwordEncoder)).getId();
    }
}