package com.ssafy.sai.domain.member.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class FindIdRequest {

    @NotBlank(message = "이름은 필수 입력값입니다.")
    String name;

    @NotNull(message = "생년월일은 필수 입력값입니다.")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    LocalDate birthday;
}
