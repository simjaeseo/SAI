package com.ssafy.sai.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Campus {

    private String city;
    private int classNum;

    protected Campus() {
    }

    public Campus(String city, int classNum) {
        this.city = city;
        this.classNum = classNum;
    }
}
