package com.ssafy.sai.domain.member.dto.response;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class ConsultantResponse {

    private Long id;
    private String email;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private MemberStatus memberStatus;
    private CampusConsultantResponse campus;
    private String phone;
    private ProfileResponse profilePicture;

    public ConsultantResponse(Member member) {
        id = member.getId();
        email = member.getEmail();
        name = member.getName();
        birthday = member.getBirthday();
        memberStatus = member.getMemberStatus();
        campus = new CampusConsultantResponse(member.getCampus());
        phone = member.getPhone();
        if (member.getProfilePicture() != null) {
            profilePicture = new ProfileResponse(member.getProfilePicture());
        }
    }
}
