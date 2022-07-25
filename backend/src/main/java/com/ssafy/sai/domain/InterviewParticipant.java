package com.ssafy.sai.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class InterviewParticipant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_participant_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "interview_room_id")
    private InterviewRoom interviewRoom;
}
