package com.ssafy.sai.domain.interview.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class UseInterviewQuestion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "use_interview_question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="interview_info_id")
    private InterviewInfo savedInterviewInfo;

    private String question;

}
