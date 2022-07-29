package com.ssafy.sai.api.request;

import com.ssafy.sai.db.entity.Enterprise;
import com.ssafy.sai.db.entity.FavoriteEnterprise;
import com.ssafy.sai.db.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteEnterpriseCreateRequest {

    @NotEmpty
    private Enterprise enterprise;

    @NotEmpty
    private Member member;

    public FavoriteEnterprise toEntity() {
        return FavoriteEnterprise.builder()
                .enterprise(enterprise)
                .member(member)
                .build();
    }

}
