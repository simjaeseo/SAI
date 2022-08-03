package com.ssafy.sai.domain.interview.service;

import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.interview.domain.QuestionType;
import com.ssafy.sai.domain.interview.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewService {

    private final QuestionRepository questionRepository;

    @Transactional
    public InterviewQuestion getRandomQuestion(QuestionType questionType){
        List<InterviewQuestion> questions = questionRepository.findAllByQuestionType(questionType);
        return questions.get((int)Math.random()*(questions.size()));
    }

}
