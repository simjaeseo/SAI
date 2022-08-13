package com.ssafy.sai.domain.member.dto.response;

import com.ssafy.sai.domain.member.domain.Member;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ConsultantUpdateResponse {

    private Long id;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private CampusConsultantResponse campus;
    private String phone;
    private ProfileResponse profilePicture;

    public ConsultantUpdateResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.birthday = member.getBirthday();
        this.campus = new CampusConsultantResponse(member.getCampus());
        this.phone = member.getPhone();
        this.profilePicture = new ProfileResponse(member.getProfilePicture());
    }
}
