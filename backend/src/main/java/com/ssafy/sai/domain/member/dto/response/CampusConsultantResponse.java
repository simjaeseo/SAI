package com.ssafy.sai.domain.member.dto.response;

import com.ssafy.sai.domain.member.domain.Campus;
import lombok.Data;

@Data
public class CampusConsultantResponse {

    private String city;

    public CampusConsultantResponse(Campus campus) {
        this.city = campus.getCity();
    }
}
