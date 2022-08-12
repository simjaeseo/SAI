package com.ssafy.sai.domain.interview.domain;

import com.ssafy.sai.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class InterviewVideo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_video_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "interview_info_id")
    private InterviewInfo interviewInfo;

    private String videoUrl;

    private String feedback;

    private String stt;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "use_interview_question_id")
    private UseInterviewQuestion useInterviewQuestion;

    public void createFeedback(String feedback) {
        this.feedback = feedback;
    }
}
