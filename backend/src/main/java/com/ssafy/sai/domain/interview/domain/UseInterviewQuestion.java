package com.ssafy.sai.domain.interview.domain;

import com.ssafy.sai.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UseInterviewQuestion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "use_interview_question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="saved_interview_info_id")
    private SavedInterviewInfo savedInterviewInfo;

    private String question;

    private String feedback;

    private LocalTime questionStartTime;

    private LocalTime answerEndTime;
}
