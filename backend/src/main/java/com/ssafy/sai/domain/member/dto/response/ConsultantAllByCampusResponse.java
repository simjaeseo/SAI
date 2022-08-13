package com.ssafy.sai.domain.member.dto.response;

import com.ssafy.sai.domain.member.domain.Member;
import lombok.Data;

@Data
public class ConsultantAllByCampusResponse {

    private Long id;
    private String name;

    public ConsultantAllByCampusResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
    }
}
