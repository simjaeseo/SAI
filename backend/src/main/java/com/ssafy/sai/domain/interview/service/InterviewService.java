package com.ssafy.sai.domain.interview.service;

import com.ssafy.sai.domain.interview.domain.*;
import com.ssafy.sai.domain.interview.dto.CreateInterviewInfoRequest;
import com.ssafy.sai.domain.interview.dto.request.CustomQuestionRequest;
import com.ssafy.sai.domain.interview.repository.InterviewInfoRepository;
import com.ssafy.sai.domain.interview.repository.UseInterviewQuestionRepository;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.interview.repository.CustomQuestionRepository;
import com.ssafy.sai.domain.interview.repository.QuestionRepository;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.repository.MemberRepository;
import com.ssafy.sai.domain.schedule.domain.Schedule;
import com.ssafy.sai.domain.schedule.exception.ScheduleException;
import com.ssafy.sai.domain.schedule.exception.ScheduleExceptionType;
import com.ssafy.sai.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewService {

    private final QuestionRepository questionRepository;
    private final CustomQuestionRepository customQuestionRepository;

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final InterviewInfoRepository interviewInfoRepository;
    private final UseInterviewQuestionRepository useInterviewQuestionRepository;

    @Transactional
    public Optional<InterviewQuestion> getQuestion(Long id) {
        Optional<InterviewQuestion> question = questionRepository.findById(id);
        return question;
    }

    @Transactional
    public Optional<CustomInterviewQuestion> getCustomQuestion(Long id) {
        Optional<CustomInterviewQuestion> question = customQuestionRepository.findById(id);
        return question;
    }

    @Transactional
    public List<InterviewQuestion> getQuestionList(String questionType, String questionDetailType) {
        List<InterviewQuestion> questionList = questionRepository.findAllByQuestionTypeAndQuestionDetailType(questionType, questionDetailType);
        return questionList;
    }

    @Transactional
    public InterviewQuestion getRandomQuestion(String questionType, String questionDetailType) {
        List<InterviewQuestion> findQuestionList = questionRepository.findAllByQuestionTypeAndQuestionDetailType(questionType, questionDetailType);
//        List<InterviewQuestion> questions = questionRepository.findAllByQuestionTypeAndQuestionDetailType(request);
        return findQuestionList.get((int) Math.random() * (findQuestionList.size()));
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
    public void deleteCustomInterviewQuestion(Long id) {
        CustomInterviewQuestion customQuestion = customQuestionRepository.findById(id).get();
        customQuestionRepository.delete(customQuestion);
    }

    @Transactional
    public InterviewInfo createInterviewInfo(Long id, CreateInterviewInfoRequest request) throws ScheduleException {

        InterviewInfo interviewInfo;

        //나혼자 연습일때
        if (request.getScheduleId() == null) {
            Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

            interviewInfo = InterviewInfo.builder().memberStudent(findMember)
                    .feedbackRequestStatus(request.getFeedbackRequest().equals("true") ? FeedbackRequestStatus.TRUE : FeedbackRequestStatus.FALSE)
                    .feedbackCompleteStatus(FeedbackCompleteStatus.FALSE)
                    .interviewDate(LocalDate.now())
                    .build();

        } else {
            // 컨설턴트와 면접일때
            Schedule findSchedule = scheduleRepository.findById(request.getScheduleId()).orElseThrow(() -> new ScheduleException(ScheduleExceptionType.NOT_FOUND_SCHEDULE));

            interviewInfo = InterviewInfo.builder().memberStudent(findSchedule.getMemberStudent())
                    .memberConsultant(findSchedule.getMemberConsultant())
                    .feedbackRequestStatus(request.getFeedbackRequest().equals("true") ? FeedbackRequestStatus.TRUE : FeedbackRequestStatus.FALSE)
                    .feedbackCompleteStatus(FeedbackCompleteStatus.FALSE)
                    .interviewDate(LocalDate.now())
                    .category(findSchedule.getCategory())
                    .detail(findSchedule.getDetail())
                    .build();
        }

        InterviewInfo saveInterviewInfo = interviewInfoRepository.save(interviewInfo);

        return saveInterviewInfo;

//        // 사용한 면접 질문 넣기
//        for (String question : request.getQuestions()) {
//            UseInterviewQuestion useInterviewQuestion = UseInterviewQuestion.builder()
//                    .savedInterviewInfo(saveInterviewInfo)
//                    .question(question).build();
//
//            useInterviewQuestionRepository.save(useInterviewQuestion);
//        }

    }

    }

}
