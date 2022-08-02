package com.ssafy.sai.domain.member.dto;

import com.ssafy.sai.domain.member.domain.Campus;
import lombok.Data;

@Data
public class CampusConsultantDto {
    private String city;

    public CampusConsultantDto() {
    }

    public CampusConsultantDto(Campus campus) {
        this.city = campus.getCity();
    }
}
