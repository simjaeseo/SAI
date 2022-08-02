package com.ssafy.sai.domain.member.dto;

import com.ssafy.sai.domain.member.domain.Campus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MemberUpdateResponse {

    private Long id;
    private String email;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthday;
    private Campus campus;
    private String phone;
    private String profilePicture;
}
