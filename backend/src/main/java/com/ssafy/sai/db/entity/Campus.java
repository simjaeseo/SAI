package com.ssafy.sai.db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Campus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campus_id")
    private Long id;

    private String name;

    @Column(name = "class")
    private int classNumber;

    @OneToMany(mappedBy = "campus")
    List<Member> members = new ArrayList<>();
}
