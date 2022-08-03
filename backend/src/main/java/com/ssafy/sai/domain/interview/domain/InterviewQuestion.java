package com.ssafy.sai.domain.interview.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterviewQuestion{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_question_id")
    private Long id;

    private String question;

    private QuestionType questionType;



}
