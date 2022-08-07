package com.ssafy.sai.domain.interview.api;

import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.interview.dto.CustomQuestionDto;
import com.ssafy.sai.domain.interview.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;


    @PostMapping("/interview-question")
    public Optional<CustomInterviewQuestion> createCustomInterviewQuestion(@PathVariable CustomQuestionDto customQuestionDto){
        Optional<CustomInterviewQuestion> customQuestion = interviewService.createCustomInterviewQuestion(customQuestionDto);
        return customQuestion;
    }

    @DeleteMapping("/interview-question")


    //질문 조회
    @GetMapping("/interview-question")
    public Optional<InterviewQuestion> getQuestion(@PathVariable Long id){
        return interviewService.getQuestion(id);
    }

    //질문 목록 조회
    @GetMapping("/interview_question")
    public List<InterviewQuestion> getQuestionList(@PathVariable String questionType, @PathVariable String questionDetailType){

        List<InterviewQuestion> questionList = interviewService.getQuestionList(questionType,questionDetailType);
        return questionList;
    }

    //랜덤 질문 받기
    @GetMapping("/interview-question")
    public InterviewQuestion getRandomQuestion(@PathVariable String questionType,@PathVariable String questionDetailType){
        return interviewService.getRandomQuestion(questionType, questionDetailType);
    }



}
