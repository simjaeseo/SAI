package com.ssafy.sai.domain.interview.service;

import com.ssafy.sai.domain.interview.domain.*;

import com.ssafy.sai.domain.interview.dto.request.CreateInterviewInfoRequest;
import com.ssafy.sai.domain.interview.dto.InterviewInfoDto;
import com.ssafy.sai.domain.interview.dto.InterviewVideoDto;
import com.ssafy.sai.domain.interview.dto.request.CustomQuestionRequest;
import com.ssafy.sai.domain.interview.dto.request.RequestFeedbackRequest;
import com.ssafy.sai.domain.interview.dto.response.SaveFeedbackResponse;
import com.ssafy.sai.domain.interview.dto.request.FeedbackRequest;
import com.ssafy.sai.domain.interview.exception.InterviewException;
import com.ssafy.sai.domain.interview.exception.InterviewExceptionType;
import com.ssafy.sai.domain.interview.repository.InterviewInfoRepository;
import com.ssafy.sai.domain.interview.repository.InterviewVideoRepository;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewService {

    private final QuestionRepository questionRepository;
    private final CustomQuestionRepository customQuestionRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final InterviewInfoRepository interviewInfoRepository;
    private final InterviewVideoRepository interviewVideoRepository;

//    private final UseInterviewQuestionRepository useInterviewQuestionRepository;

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


    /**
     * @메소드 면접 저장 서비스 (면접 정보 테이블에 저장)
     * @param id 사용자 id
     * @param request 일정id, 피드백요청 유무, 컨설턴트id, 면접영상url 배열(openvidu server 안), 질문배열
     * @return InterviewInfo 엔티티
     * @throws ScheduleException
     */
    @Transactional
    public InterviewInfo createInterviewInfo(Long id, CreateInterviewInfoRequest request) throws ScheduleException {

        InterviewInfo interviewInfo;

        //나혼자 연습일때
        if (request.getScheduleId() == null) {
            Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

            Member findConsultant = null;
            if(request.getFeedbackRequest().equals("true")){
                findConsultant = memberRepository.findById(Long.parseLong(request.getConsultantId())).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
            }

            interviewInfo = InterviewInfo.builder().memberStudent(findMember)
                    .memberConsultant(request.getFeedbackRequest().equals("true") ? findConsultant : null)
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

    }


    public List<InterviewInfoDto> findFeedbackRequest(Long id) {
        Member findMember = memberRepository.findMemberById(id);
        List<InterviewInfo> feedbackRequestList = interviewInfoRepository.findInterviewInfoByFeedbackRequest(findMember.getId());
        List<InterviewInfoDto> result = feedbackRequestList.stream()
                .map(interviewInfo -> new InterviewInfoDto(interviewInfo))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * @param consultantId 컨설턴트 PK
     * @param infoId       면접 정보 PK
     * @return 면접 질문별 영상
     * @메소드 면접 영상 가져오는 서비스
     */
    public List<InterviewVideoDto> findInterviewVideo(Long consultantId, Long infoId) {
        Member findMember = memberRepository.findById(consultantId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.WRONG_MEMBER_INFORMATION));
        InterviewInfo interviewInfo = interviewInfoRepository.findInfoById(findMember.getId(), infoId).orElseThrow(() -> new InterviewException(InterviewExceptionType.BAD_REQUEST));
        List<InterviewVideo> interviewVideoList = interviewInfo.getInterviewVideoList();
        List<InterviewVideoDto> result = interviewVideoList.stream()
                .map(interviewVideo -> new InterviewVideoDto(interviewVideo))
                .collect(Collectors.toList());

        return result;
    }

    @Transactional
    public SaveFeedbackResponse saveFeedback(Long consultantId, Long videoId, FeedbackRequest request) {
        memberRepository.findById(consultantId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.WRONG_MEMBER_INFORMATION));

        InterviewVideo interviewVideo = interviewVideoRepository.findById(videoId)
                .orElseThrow(() -> new InterviewException(InterviewExceptionType.BAD_REQUEST));

        interviewVideo.createFeedback(request.getFeedback());

        return new SaveFeedbackResponse(LocalDateTime.now(), request.getFeedback());
    }

    @Transactional
    public void finishFeedback(Long consultantId, Long infoId) {
        Member findMember = memberRepository.findById(consultantId)
                .orElseThrow(() -> new MemberException(MemberExceptionType.WRONG_MEMBER_INFORMATION));

        InterviewInfo interviewInfo = interviewInfoRepository.findInfoById(findMember.getId(), infoId)
                .orElseThrow(() -> new InterviewException(InterviewExceptionType.BAD_REQUEST));

        interviewInfo.changeFeedbackStatus(FeedbackCompleteStatus.TRUE);
    }

    //    - 면접 세부 분석 (교육생 입장)
    //    혼자 연습 + 컨설팅 연습 관련 정보 불러오기 - 재서
    @Transactional
    public List<InterviewInfo> selectInterviewInfoList(Long id) throws MemberException{

        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        return interviewInfoRepository.selectAllByMember(findMember);

    }

    //    - 면접 세부 분석 (교육생 입장)
    //    피드백 요청하기 - 재서
    @Transactional
    public void requestFeedback(Long id, RequestFeedbackRequest request) throws InterviewException, MemberException {
        InterviewInfo findInterviewInfo = interviewInfoRepository.findById(request.getInterviewInfoId()).orElseThrow(() -> new InterviewException(InterviewExceptionType.NOT_FOUND_INTERVIEW_INFO));

        Member findConsultant = memberRepository.findById(request.getConsultantId()).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        findInterviewInfo.builder()
                .memberConsultant(findConsultant)
                .feedbackRequestStatus(FeedbackRequestStatus.TRUE)
                .build();

        interviewInfoRepository.save(findInterviewInfo);


    }

    //    - 마이페이지
    //    영상 삭제 - 재서
    @Transactional
    public void deleteInterview(Long id, Long interviewInfoId){

        InterviewInfo findInterviewInfo = interviewInfoRepository.findById(interviewInfoId).orElseThrow(() -> new InterviewException(InterviewExceptionType.NOT_FOUND_INTERVIEW_INFO));

        InterviewVideo findInterviewVideo = interviewVideoRepository.findByInterviewInfo(findInterviewInfo).orElseThrow(() -> new InterviewException(InterviewExceptionType.NOT_FOUND_INTERVIEW_VIDEO));

        interviewInfoRepository.delete(findInterviewInfo);
        interviewVideoRepository.delete(findInterviewVideo);
    }
}