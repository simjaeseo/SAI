package com.ssafy.sai.domain.job.dto;

import com.ssafy.sai.domain.job.domain.Enterprise;
import com.ssafy.sai.domain.job.domain.InterestedEnterprise;
import com.ssafy.sai.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InterestedEnterpriseCreateRequest {

    @NotEmpty
    private Enterprise enterprise;

    @NotEmpty
    private Member member;

    public InterestedEnterprise toEntity() {
        return InterestedEnterprise.builder()
                .enterprise(enterprise)
                .member(member)
                .build();
    }

}
