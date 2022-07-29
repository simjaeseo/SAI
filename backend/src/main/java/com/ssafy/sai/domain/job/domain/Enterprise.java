package com.ssafy.sai.domain.job.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Enterprise {

    @Id @GeneratedValue
    @Column(name = "enterprise_id")
    private Long id;

    private String name;
}
