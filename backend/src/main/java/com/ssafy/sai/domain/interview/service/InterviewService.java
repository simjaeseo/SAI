package com.ssafy.sai.domain.interview.service;

import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.interview.dto.request.CustomQuestionRequest;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.interview.repository.CustomQuestionRepository;
import com.ssafy.sai.domain.interview.repository.QuestionRepository;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewService {

    private final QuestionRepository questionRepository;
    private final CustomQuestionRepository customQuestionRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public Optional<InterviewQuestion> getQuestion(Long id){
        Optional<InterviewQuestion> question = questionRepository.findById(id);
        return question;
    }
    @Transactional
    public Optional<CustomInterviewQuestion> getCustomQuestion(Long id){
        Optional<CustomInterviewQuestion> question = customQuestionRepository.findById(id);
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
    public List<CustomInterviewQuestion> getCustomInterviewQuestionList(CustomQuestionRequest request) throws MemberException {
        Member findMember = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
        List<CustomInterviewQuestion> customInterviewQuestionList = customQuestionRepository.findQuestionsByMemberId(findMember.getId());
        return customInterviewQuestionList;
    }

    @Transactional
    public void createCustomInterviewQuestion(CustomQuestionRequest request) throws MemberException {
        Member findMember = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
        CustomInterviewQuestion question = new CustomInterviewQuestion(request.getQuestion(), findMember);
        customQuestionRepository.save(question);

    }

    @Transactional
    public void deleteCustomInterviewQuestion(Long id){
            CustomInterviewQuestion customQuestion = customQuestionRepository.findById(id).get();
            customQuestionRepository.delete(customQuestion);

    }

}
