package com.ssafy.sai.domain.member.dto.response;

import lombok.Data;

@Data
public class CampusResponse {

    private String city;
    private Integer classNumber;

    public CampusResponse(String city, Integer classNumber) {
        this.city = city;
        this.classNumber = classNumber;
    }
}
