package com.ssafy.sai.domain.interview.domain;

import com.ssafy.sai.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feedback extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long id;

    private String feedback;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "interview_video_id")
    private InterviewVideo interviewVideo;
}
