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

    @JsonBackReference
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "interview_info_id")
    private InterviewInfo interviewInfo;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "use_interview_question_id")
    private UseInterviewQuestion useInterviewQuestion;

    private String videoUrl;

    private String videoName;

    private String audioUrl;

    private String audioName;
    private String feedback;

    private String stt;

    private String wrongPostureCount;

    public void createFeedback(String feedback) {
        this.feedback = feedback;
    }
}
