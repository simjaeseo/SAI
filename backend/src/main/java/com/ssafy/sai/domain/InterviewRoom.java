package com.ssafy.sai.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class InterviewRoom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_room_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private InterviewType iType;

    @JoinColumn("schedule_id")
    private Schedule schedule;
}
