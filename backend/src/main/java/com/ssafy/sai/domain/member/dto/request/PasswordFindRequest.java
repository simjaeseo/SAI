package com.ssafy.sai.domain.member.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class PasswordFindRequest {

    private Long id;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}",
            message = "비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다.")
    private String password;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}",
            message = "비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다.")
    private String newPassword;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}",
            message = "비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다.")
    private String newPasswordCheck;

    public PasswordFindRequest(Long id, String password, String newPassword, String newPasswordCheck) {
        this.id = id;
        this.password = password;
        this.newPassword = newPassword;
        this.newPasswordCheck = newPasswordCheck;
    }
}
