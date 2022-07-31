package com.ssafy.sai.domain.member.dto;

import com.ssafy.sai.domain.job.dto.EnterpriseName;
import com.ssafy.sai.domain.job.dto.JobName;
import com.ssafy.sai.domain.member.domain.Member;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MemberUpdateRequest {

    private Long id;

    @NotNull(message = "캠퍼스 정보는 필수 입력값입니다.")
    private CampusDto campus;

    @NotBlank(message = "연락처는 필수 입력값입니다.")
    private String phone;

    private String profilePicture;

    private List<EnterpriseName> interestedEnterprises;
    private List<JobName> interestedJobs;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .phone(phone)
                .profilePicture(profilePicture)
                .build();
    }
}
