package com.ssafy.sai.domain.member.dto.response;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.dto.CampusDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ConsultantUpdateResponse {

    private Long id;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private CampusConsultantDto campus;
    private String phone;
    private ProfileDto profilePicture;

    public ConsultantUpdateResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.birthday = member.getBirthday();
        this.campus = new CampusConsultantDto(member.getCampus());
        this.phone = member.getPhone();
        this.profilePicture = new ProfileDto(member.getProfilePicture());
    }
}
