package com.ssafy.sai.domain.interview.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class UsedInterviewQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_interview_question_id")
    private Long id;

    private String question;

    @OneToOne(mappedBy = "useInterviewQuestion", cascade = CascadeType.ALL)
    private InterviewVideo interviewVideo;

    public void addInterviewVideo(InterviewVideo interviewVideo){
        this.interviewVideo = interviewVideo;
        interviewVideo.setUseInterviewQuestion(this);
    }
}
