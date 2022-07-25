package com.ssafy.sai.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(nullable = false)

    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "birthday")
    private String birth;

    @Column(name = "phone")
    private String phone;

    @Column(name = "member_is_consultant")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Column(name = "profile_picture_url")
    private String profile;
}
