package com.ssafy.sai.domain.member.dto.response;

import lombok.Data;

@Data
public class ConsultantAllByCampusResponse {

    private Long id;

    private String name;

    public ConsultantAllByCampusResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
