package com.ssafy.sai.domain.interview.api;

import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.interview.domain.QuestionType;
import com.ssafy.sai.domain.interview.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;

    //질문
    @GetMapping("/{question_type}")
    public InterviewQuestion getQuestion(@PathVariable QuestionType questionType){
        return interviewService.getRandomQuestion(questionType);
    }

    //랜덤 질문 받기
    @GetMapping("/{question_type}")
    public InterviewQuestion getRandomQuestion(@PathVariable QuestionType questionType){
        return interviewService.getRandomQuestion(questionType);
    }



}
