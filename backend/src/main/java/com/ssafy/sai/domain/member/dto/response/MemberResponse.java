package com.ssafy.sai.domain.member.dto.response;

import com.ssafy.sai.domain.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponse {

    private String email;
    private String name;

    public MemberResponse(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
    }

}