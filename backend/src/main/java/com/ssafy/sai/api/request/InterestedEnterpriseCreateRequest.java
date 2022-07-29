package com.ssafy.sai.api.request;

import com.ssafy.sai.db.entity.Enterprise;
import com.ssafy.sai.db.entity.InterestedEnterprise;
import com.ssafy.sai.db.entity.Member;
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
