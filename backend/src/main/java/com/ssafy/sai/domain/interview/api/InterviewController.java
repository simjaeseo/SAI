package com.ssafy.sai.domain.interview.api;

import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.interview.dto.CustomQuestionDto;
import com.ssafy.sai.domain.interview.dto.QuestionDto;
import com.ssafy.sai.domain.interview.dto.questionRequest.QuestionRequest;
import com.ssafy.sai.domain.interview.service.InterviewService;
import com.ssafy.sai.domain.member.domain.Member;
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


//    @PostMapping("/question")
//    public Optional<CustomInterviewQuestion> createCustomInterviewQuestion(@PathVariable CustomQuestionDto customQuestionDto){
//        Optional<CustomInterviewQuestion> customQuestion = interviewService.createCustomInterviewQuestion(customQuestionDto);
//        return customQuestion;
//    }

//    @DeleteMapping("/question")


    @GetMapping("/customQuestion")
    public List<CustomInterviewQuestion> getCustomInterviewQuestionList(@RequestParam Member member){
        return interviewService.getCustomInterviewQuestionList(member);
    }


    //질문 조회
    @GetMapping("/question")
    public Optional<InterviewQuestion> getQuestion(@RequestParam Long id){
        return interviewService.getQuestion(id);
    }

    //질문 목록 조회
    @GetMapping("/question/list")
    public List<InterviewQuestion> getQuestionList(@RequestParam String questionType, @RequestParam String questionDetailType){
        List<InterviewQuestion> questionList = interviewService.getQuestionList(questionType,questionDetailType);
        return questionList;
    }

    //랜덤 질문 받기
    @GetMapping("/question/random")
    public InterviewQuestion getRandomQuestion(@RequestParam String questionType, @RequestParam String questionDetailType){
        return interviewService.getRandomQuestion(questionType,questionDetailType);
    }



}
