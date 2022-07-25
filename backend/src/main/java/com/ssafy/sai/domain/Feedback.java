package com.ssafy.sai.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Feedback {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long id;

    @Column(name = "consultant_feedback")
    private String consultantFeedback;
}
