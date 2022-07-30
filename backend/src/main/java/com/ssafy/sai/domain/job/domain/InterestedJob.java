package com.ssafy.sai.domain.job.domain;

import com.ssafy.sai.domain.member.domain.Member;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InterestedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interested_job_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public InterestedJob(Job job, Member member) {
        this.job = job;
        this.member = member;
    }
}
