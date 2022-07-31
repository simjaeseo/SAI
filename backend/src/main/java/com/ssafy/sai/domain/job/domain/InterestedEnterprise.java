package com.ssafy.sai.domain.job.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.sai.domain.member.domain.Member;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InterestedEnterprise {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interested_enterprise_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @JsonBackReference
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
