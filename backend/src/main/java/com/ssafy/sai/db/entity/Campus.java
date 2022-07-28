package com.ssafy.sai.db.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Campus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campus_id")
    private Long id;

    private String name;

    @Column(name = "class")
    private int classNumber;

    @OneToMany(mappedBy = "campus")
    List<Member> members = new ArrayList<>();

    public Campus(Long i, String name, int classNumber) {
        this.id = i;
        this.name = name;
        this.classNumber = classNumber;
    }
}
