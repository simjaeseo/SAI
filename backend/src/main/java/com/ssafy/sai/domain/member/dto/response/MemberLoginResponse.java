package com.ssafy.sai.domain.member.dto.response;

import com.ssafy.sai.domain.member.domain.MemberStatus;
import com.ssafy.sai.domain.member.dto.response.CampusResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginResponse {

    private Long id;
    private String email;
    private String name;
    private String phone;
    private CampusResponse campus;
    private LocalDate birthday;
    private MemberStatus memberStatus;
}