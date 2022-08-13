package com.ssafy.sai.domain.interview.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.sai.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Setter
public class InterviewVideo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_video_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "interview_info_id")
    private InterviewInfo interviewInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "used_interview_question_id")
    private UsedInterviewQuestion usedInterviewQuestion;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "video_name")
    private String videoName;

    @Column(name = "audio_url")
    private String audioUrl;

    @Column(name = "audio_name")
    private String audioName;
    private String feedback;

    private String stt;

    @Column(name = "wrong_posture_count")
    private String wrongPostureCount;

    public void createFeedback(String feedback) {
        this.feedback = feedback;
    }
}
