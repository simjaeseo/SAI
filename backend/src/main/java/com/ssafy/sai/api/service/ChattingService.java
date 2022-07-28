package com.ssafy.sai.api.service;

import com.ssafy.sai.db.entity.Chatting;
import com.ssafy.sai.db.entity.Member;
import com.ssafy.sai.db.repository.ChattingRepository;
import com.ssafy.sai.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChattingService {
    private final MemberRepository memberRepository;
    private final ChattingRepository chattingRepository;

    public Chatting chattingHandler(Chatting chatting) {
        Member member = memberRepository.findByEmail(chatting.getMember().getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        chatting.setMember(member);

        return chattingRepository.save(chatting);
    }
}