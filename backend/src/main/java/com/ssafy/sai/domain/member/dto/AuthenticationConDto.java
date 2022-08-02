package com.ssafy.sai.domain.member.dto;

import com.ssafy.sai.domain.member.domain.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationConDto {

    private Long id;
    private String email;
    private String name;
    private String phone;
    private CampusConsultantDto campus;
    private LocalDate birthday;
    private MemberStatus memberStatus;
}
