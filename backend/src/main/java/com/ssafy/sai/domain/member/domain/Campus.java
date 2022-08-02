package com.ssafy.sai.domain.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Campus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campus_id")
    private Long id;

    private String city;

    @Column(name = "class")
    private Integer classNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL)
    List<Member> members = new ArrayList<>();
}
