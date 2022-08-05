package com.ssafy.sai.domain.member.dto.response;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.dto.CampusDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MemberUpdateResponse {

    private Long id;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private CampusDto campus;
    private String phone;
    private ProfileDto profilePicture;

    public MemberUpdateResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.birthday = member.getBirthday();
        this.campus = new CampusDto(member.getCampus().getCity(), member.getCampus().getClassNumber());
        this.phone = member.getPhone();
        this.profilePicture = new ProfileDto(member.getProfilePicture());
    }
}
