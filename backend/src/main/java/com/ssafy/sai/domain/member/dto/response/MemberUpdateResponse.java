package com.ssafy.sai.domain.member.dto.response;

import com.ssafy.sai.domain.member.domain.Member;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MemberUpdateResponse {

    private Long id;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private CampusResponse campus;
    private String phone;
    private ProfileResponse profilePicture;

    public MemberUpdateResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.birthday = member.getBirthday();
        this.campus = new CampusResponse(member.getCampus().getCity(), member.getCampus().getClassNumber());
        this.phone = member.getPhone();
        if (member.getProfilePicture() != null) {
            this.profilePicture = new ProfileResponse(member.getProfilePicture());
        }
    }
}
