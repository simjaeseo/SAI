package com.ssafy.sai.domain.member.dto;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import com.ssafy.sai.domain.member.domain.ProfilePicture;
import com.ssafy.sai.domain.member.dto.response.CampusConsultantDto;
import com.ssafy.sai.domain.member.dto.response.ProfileDto;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class ConsultantDto {
    private Long id;
    private String email;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private MemberStatus memberStatus;
    private CampusConsultantDto campus;
    private String phone;
    private ProfileDto profilePicture;

    public ConsultantDto(Member member) {
        id = member.getId();
        email = member.getEmail();
        name = member.getName();
        birthday = member.getBirthday();
        memberStatus = member.getMemberStatus();
        campus = new CampusConsultantDto(member.getCampus());
        phone = member.getPhone();
        if (member.getProfilePicture() != null) {
            profilePicture = new ProfileDto(member.getProfilePicture());
        }
    }
}