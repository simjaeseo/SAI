package com.ssafy.sai.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Campus {

    @Id @GeneratedValue
    @Column(name = "campus_id")
    private Long id;

    private String city;

    @Column(name = "class")
    private int classNumber;

    @OneToMany(mappedBy = "campus")
    List<Member> members = new ArrayList<>();
}
