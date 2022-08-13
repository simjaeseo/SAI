package com.ssafy.sai.domain.interview.api;

import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.interview.domain.InterviewVideo;
import com.ssafy.sai.domain.interview.dto.request.*;
import com.ssafy.sai.domain.interview.dto.InterviewInfoDto;
import com.ssafy.sai.domain.interview.dto.InterviewVideoDto;
import com.ssafy.sai.domain.interview.dto.response.SaveFeedbackResponse;
import com.ssafy.sai.domain.interview.service.GcsService;
import com.ssafy.sai.domain.interview.service.InterviewService;
import com.ssafy.sai.domain.interview.service.S3Service;
import com.ssafy.sai.global.common.DataResponse;
import com.ssafy.sai.global.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;
    private final S3Service s3Service;
    private final GcsService gcsService;

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


    /**
     * @메소드 면접 저장 api
     * @param id 사용자id
     * @param request 일정id, 피드백요청 유무, 컨설턴트id, 면접영상url 배열(openvidu server 안), 질문배열
     * @return MessageResponse
     */
    @PostMapping("/{member_id}")
    public ResponseEntity<? extends MessageResponse> saveInterview(@PathVariable("member_id") Long id, @RequestBody CreateInterviewInfoRequest request){

        InterviewInfo saveInterviewInfo = interviewService.createInterviewInfo(id, request);

        try {
            s3Service.uploadFileS3(id, request, saveInterviewInfo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    //    - 면접 세부 분석 (교육생 입장)
    //    혼자 연습 + 컨설팅 연습 관련 정보 불러오기 - 재서

    /**
     * @메소드 모든 면접 정보 조회 api
     * @param id 멤버id
     * @return List<InterviewInfoResponse>
     */
    @GetMapping("/{member_id}")
    public ResponseEntity<? extends MessageResponse> selectInterviewInfoList(@PathVariable("member_id") Long id){
        return ResponseEntity.ok().body(new DataResponse(interviewService.selectInterviewInfoList(id)));
    }

    //    - 면접 세부 분석 (교육생 입장)
    //    피드백 요청하기 - 재서

    /**
     * @메소드 피드백 요청
     * @param id 멤버id
     * @param request interviewInfoId, consultnantId
     * }
     * @return
     */
    @PostMapping("/{member_id}/feedback")
    public ResponseEntity<? extends MessageResponse> requestFeedback(@PathVariable("member_id") Long id, @RequestBody RequestFeedbackRequest request){
        interviewService.requestFeedback(id, request);

        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    //    - 마이페이지
    //    영상 삭제 - 재서
    @DeleteMapping("/{member_id}/{saved_interview_info_id}")
    public void deleteInterview(@PathVariable("member_id") Long id, @PathVariable("saved_interview_info_id") Long interviewInfoId){


        List<InterviewVideo> findInterviewVideos = interviewService.selectS3VideoNameList(interviewInfoId);



        //s3에 삭제하고
        s3Service.deleteFileS3(findInterviewVideos);


        // gcs 삭제하고
        try {
            gcsService.deleteFileGcs(findInterviewVideos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // db에 삭제하고
        interviewService.deleteInterview(id, interviewInfoId);
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
