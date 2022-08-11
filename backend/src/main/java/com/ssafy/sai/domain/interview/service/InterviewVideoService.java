package com.ssafy.sai.domain.interview.service;

import com.ssafy.sai.domain.interview.repository.InterviewVideoRepository;
import com.ssafy.sai.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewVideoService {

    private final InterviewVideoRepository interviewVideoRepository;
    private final MemberRepository memberRepository;
}
