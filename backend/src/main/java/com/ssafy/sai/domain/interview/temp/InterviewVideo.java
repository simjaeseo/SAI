package com.ssafy.sai.domain.interview.temp;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterviewVideo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_video_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "interview_participant_id")
    private InterviewParticipant interviewParticipant;

    private String videoUrl;

    @OneToMany(mappedBy = "interviewVideo")
    private List<Feedback> feedbacks = new ArrayList<>();
}
