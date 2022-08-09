package com.ssafy.sai.domain.interview.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.sai.domain.interview.dto.request.CustomQuestionRequest;
import com.ssafy.sai.domain.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomInterviewQuestion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custom_interview_question_id")
    private Long id;

    private String question;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;


    public CustomInterviewQuestion(String question, Member member) {
        this.question = question;
        this.member = member;
    }
}
