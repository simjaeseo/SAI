package com.ssafy.sai.domain.member.dto.request;

import com.ssafy.sai.domain.member.dto.response.CampusResponse;
import com.ssafy.sai.domain.member.dto.response.ProfileResponse;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConsultantUpdateRequest {

    private Long id;

    @NotNull(message = "캠퍼스 정보는 필수 입력값입니다.")
    private CampusResponse campus;

    private ProfileResponse profilePicture;
    @NotNull(message = "연락처 정보는 필수 입력값입니다.")
    private String phone;
}
