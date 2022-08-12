package com.ssafy.sai.domain.interview.api;

import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.interview.dto.CreateInterviewInfoRequest;
import com.ssafy.sai.domain.interview.dto.DeleteInterviewVideoRequest;
import com.ssafy.sai.domain.interview.dto.InterviewInfoDto;
import com.ssafy.sai.domain.interview.dto.InterviewVideoDto;
import com.ssafy.sai.domain.interview.dto.request.CustomQuestionRequest;
import com.ssafy.sai.domain.interview.dto.SaveFeedbackResponse;
import com.ssafy.sai.domain.interview.dto.request.FeedbackRequest;
import com.ssafy.sai.domain.interview.service.InterviewService;
import com.ssafy.sai.domain.interview.service.S3UploaderService;
import com.ssafy.sai.global.common.DataResponse;
import com.ssafy.sai.global.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;
    private final S3UploaderService s3UploaderService;

    @PostMapping("/{consultant-id}/feedback/{video-id}")
    public ResponseEntity<? extends MessageResponse> saveFeedback(
            @PathVariable("consultant-id") Long consultantId, @PathVariable("video-id") Long videoId, @RequestBody FeedbackRequest request) {
        SaveFeedbackResponse saveFeedbackResponse = interviewService.saveFeedback(consultantId, videoId, request);
        return ResponseEntity.ok().body(new DataResponse<>(saveFeedbackResponse));
    }

    @PutMapping("/{consultant-id}/request/{info-id}")
    public ResponseEntity<? extends MessageResponse> finishFeedback(@PathVariable("consultant-id") Long consultantId, @PathVariable("info-id") Long infoId) {
        interviewService.finishFeedback(consultantId, infoId);
        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    @GetMapping("/{consultant-id}/request/{info-id}")
    public ResponseEntity<? extends MessageResponse> findVideoList(@PathVariable("consultant-id") Long consultantId, @PathVariable("info-id") Long infoId) {
        List<InterviewVideoDto> interviewVideoList = interviewService.findInterviewVideo(consultantId, infoId);
        return ResponseEntity.ok().body(new DataResponse<>(interviewVideoList));
    }

    @GetMapping("/{consultant-id}/request")
    public ResponseEntity<? extends MessageResponse> findFeedbackRequest(@PathVariable("consultant-id") Long id) {
        List<InterviewInfoDto> feedbackRequestList = interviewService.findFeedbackRequest(id);
        return ResponseEntity.ok().body(new DataResponse<>(feedbackRequestList));
    }

    @PostMapping("/{member_id}")
    public ResponseEntity<? extends MessageResponse> saveInterview(@PathVariable("member_id") Long id, @RequestBody CreateInterviewInfoRequest request) {
//        scheduleId: 1,
//        isPractice: true,
//        feedbackRequest: true,
//        questions: ["자기소개","지원동기"]
//        form-data : .mp4

        // DTO 만들어야함

        InterviewInfo saveInterviewInfo = interviewService.createInterviewInfo(id, request);
        s3UploaderService.uploadFileS3(id, request, saveInterviewInfo);

        return ResponseEntity.ok().body(new DataResponse<>("이게 비동기 처리인가요..?"));
    }

    /**
     * Amazon S3에 업로드 된 파일을 삭제
     *
     * @return 성공 시 200 Success
     */
    @DeleteMapping("/{member_id}/video")
    public ResponseEntity deleteInterviewVideo(@PathVariable("member_id") Long memberId, @RequestBody DeleteInterviewVideoRequest request) {
        s3UploaderService.deleteFileS3(memberId, request);
        return new ResponseEntity<>(new MessageResponse<>(), HttpStatus.OK);
    }


    @PostMapping("/customQuestion/create")
    public ResponseEntity createCustomInterviewQuestion(@RequestBody CustomQuestionRequest request) {
        interviewService.createCustomInterviewQuestion(request);
        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    //사용자 질문 삭제
    @DeleteMapping("/customQuestion/delete")
    public ResponseEntity deleteCustomInterviewQuestion(@RequestParam Long id) {
        interviewService.deleteCustomInterviewQuestion(id);
        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    //질문 조회
    @GetMapping("/customQuestion")
    public Optional<CustomInterviewQuestion> getCustomQuestion(@RequestParam Long id) {
        return interviewService.getCustomQuestion(id);
    }

    //사용자 질문 목록 조회
    @GetMapping("/customQuestion/list")
    public List<CustomInterviewQuestion> getCustomInterviewQuestionList(@RequestBody CustomQuestionRequest request) {
        return interviewService.getCustomInterviewQuestionList(request);
    }


    //질문 조회
    @GetMapping("/question")
    public Optional<InterviewQuestion> getQuestion(@RequestParam Long id) {
        return interviewService.getQuestion(id);
    }

    //질문 목록 조회
    @GetMapping("/question/list")
    public List<InterviewQuestion> getQuestionList(@RequestParam String questionType, @RequestParam String questionDetailType) {
        List<InterviewQuestion> questionList = interviewService.getQuestionList(questionType, questionDetailType);
        return questionList;
    }

    //랜덤 질문 받기
    @GetMapping("/question/random")
    public InterviewQuestion getRandomQuestion(@RequestParam String questionType, @RequestParam String questionDetailType) {
        return interviewService.getRandomQuestion(questionType, questionDetailType);
    }
}
