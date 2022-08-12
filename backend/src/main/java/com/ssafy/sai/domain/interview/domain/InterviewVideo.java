package com.ssafy.sai.domain.interview.domain;

import com.ssafy.sai.domain.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class InterviewVideo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_video_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_info_id")
    private InterviewInfo interviewInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "use_interview_question_id")
    private UseInterviewQuestion useInterviewQuestion;

    @Column(name="video_url")
    private String videoUrl;

    @Column(name="audio_url")
    private String audioUrl;


    @Column(name="consultant_feedback")
    private String consultantFeedback;

    private String stt;

    @Column(name="wrong_posture_count")
    private String wrongPostureCount;

    // 음성데이터?
}
