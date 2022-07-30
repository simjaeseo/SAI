package com.ssafy.sai.domain.member.dto;

import com.ssafy.sai.domain.member.domain.Campus;
import com.ssafy.sai.domain.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MemberUpdateRequest {

    private Long id;
    @NotNull(message = "생년월일은 필수 입력값입니다.")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthday;

    @NotNull(message = "캠퍼스 정보는 필수 입력값입니다.")
    private Campus campus;

    @NotBlank(message = "연락처는 필수 입력값입니다.")
    private String phone;


    private String profilePicture;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .birthday(birthday)
                .campus(campus)
                .phone(phone)
                .profilePicture(profilePicture)
                .build();
    }
}
