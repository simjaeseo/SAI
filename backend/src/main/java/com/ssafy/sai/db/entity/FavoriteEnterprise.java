package com.ssafy.sai.db.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FavoriteEnterprise {

    // 기업쪽으로 일대다 , 회원쪽으로 다대일
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_enterprise_id")
    private Long id;

//    @OneToMany(mappedBy = "favoriteEnterprise")
//    private List<Enterprise> enterprises = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void addMember(Member member) {
        this.member = member;
    }
}
