package com.ssafy.sai.domain.interview.api;

import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.interview.dto.CustomQuestionDto;
import com.ssafy.sai.domain.interview.dto.request.CustomQuestionRequest;
import com.ssafy.sai.domain.interview.service.InterviewService;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.global.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;



    @PostMapping("/customQuestion/create")
    public ResponseEntity createCustomInterviewQuestion(@RequestBody CustomQuestionRequest request){
        interviewService.createCustomInterviewQuestion(request);
        return ResponseEntity.ok().body(new MessageResponse<>());
    }
    //사용자 질문 삭제
    @DeleteMapping("/customQuestion/delete")
    public ResponseEntity deleteCustomInterviewQuestion(@RequestParam Long id){
        interviewService.deleteCustomInterviewQuestion(id);
        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    
    //사용자 질문 목록 조회
    @GetMapping("/customQuestion/list")
    public List<CustomInterviewQuestion> getCustomInterviewQuestionList(@RequestBody CustomQuestionRequest request){
        return interviewService.getCustomInterviewQuestionList(request);
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
