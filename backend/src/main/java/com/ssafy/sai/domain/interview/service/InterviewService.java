package com.ssafy.sai.domain.interview.service;

import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.interview.dto.CustomQuestionDto;
import com.ssafy.sai.domain.interview.dto.questionRequest.QuestionRequest;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.interview.repository.CustomQuestionRepository;
import com.ssafy.sai.domain.interview.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewService {

    private final QuestionRepository questionRepository;
    private final CustomQuestionRepository customQuestionRepository;


    @Transactional
    public Optional<InterviewQuestion> getQuestion(Long id){
        Optional<InterviewQuestion> question = questionRepository.findById(id);
        return question;
    }

    @Transactional
    public List<InterviewQuestion> getQuestionList(String questionType,String questionDetailType){
        List<InterviewQuestion> questionList = questionRepository.findAllByQuestionTypeAndQuestionDetailType(questionType,questionDetailType);
        return questionList;
    }

    @Transactional
    public InterviewQuestion getRandomQuestion(String questionType, String questionDetailType){


        List<InterviewQuestion> findQuestionList = questionRepository.findAllByQuestionTypeAndQuestionDetailType(questionType,questionDetailType);

//        List<InterviewQuestion> questions = questionRepository.findAllByQuestionTypeAndQuestionDetailType(request);
        return findQuestionList.get((int)Math.random()*(findQuestionList.size()));
    }


    @Transactional
    public List<CustomInterviewQuestion> getCustomInterviewQuestionList(Member member){

        List<CustomInterviewQuestion> customInterviewQuestionList = customQuestionRepository.findByMember(member);
        return customInterviewQuestionList;
    }

//    @Transactional
//    public Optional<CustomInterviewQuestion> createCustomInterviewQuestion(CustomQuestionDto customQuestionDto){
//            CustomInterviewQuestion customInterviewQuestion = new CustomInterviewQuestion();
//            CustomInterviewQuestion.setQuestion(customInterviewQuestion.getQuestion());
//            customQuestionRepository.save(customInterviewQuestion);
//
//
//    }

    @Transactional
    public void deleteCustomInterviewQuestion(Long id){
            CustomInterviewQuestion customQuestion = customQuestionRepository.findById(id).get();
            customQuestionRepository.delete(customQuestion);
    }


}
