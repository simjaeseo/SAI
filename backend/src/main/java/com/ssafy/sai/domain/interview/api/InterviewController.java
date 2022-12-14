package com.ssafy.sai.domain.interview.api;

import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.interview.domain.InterviewInfo;
import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.interview.domain.InterviewVideo;
import com.ssafy.sai.domain.interview.dto.request.*;
import com.ssafy.sai.domain.interview.dto.response.InterviewInfoResponse;
import com.ssafy.sai.domain.interview.dto.response.InterviewVideoResponse;

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
            @PathVariable("consultant-id") Long consultantId, @PathVariable("video-id") Long videoId,
            @RequestBody FeedbackRequest request) {
        SaveFeedbackResponse saveFeedbackResponse = interviewService.saveFeedback(consultantId, videoId, request);
        return ResponseEntity.ok().body(new DataResponse<>(saveFeedbackResponse));
    }

    @PutMapping("/{consultant-id}/request/{info-id}")
    public ResponseEntity<? extends MessageResponse> finishFeedback(@PathVariable("consultant-id") Long consultantId,
                                                                    @PathVariable("info-id") Long infoId) {
        interviewService.finishFeedback(consultantId, infoId);
        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    @GetMapping("/{consultant-id}/request/{info-id}")
    public ResponseEntity<? extends MessageResponse> findVideoList(@PathVariable("consultant-id") Long consultantId,
                                                                   @PathVariable("info-id") Long infoId) {
        List<InterviewVideoResponse> interviewVideoList = interviewService.findInterviewVideo(consultantId, infoId);
        return ResponseEntity.ok().body(new DataResponse<>(interviewVideoList));
    }

    @GetMapping("/{consultant-id}/request")
    public ResponseEntity<? extends MessageResponse> findFeedbackRequest(@PathVariable("consultant-id") Long id) {
        List<InterviewInfoResponse> feedbackRequestList = interviewService.findFeedbackRequest(id);
        return ResponseEntity.ok().body(new DataResponse<>(feedbackRequestList));
    }


    /**
     * @????????? ?????? ?????? api
     * @param id ?????????id
     * @param request ??????id, ??????????????? ??????, ????????????id, ????????????url ??????(openvidu server ???), ????????????
     * @return MessageResponse
     */
    @PostMapping("/{member-id}")
    public ResponseEntity<? extends MessageResponse> saveInterview(@PathVariable("member-id") Long id, @RequestBody CreateInterviewInfoRequest request){

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

    /**
     * @????????? ?????? ?????? ?????? ?????? api
     * @param id ??????id
     * @return List<InterviewInfoResponse>
     */
    @GetMapping("/{member-id}")
    public ResponseEntity<? extends MessageResponse> selectInterviewInfoList(@PathVariable("member-id") Long id){
        return ResponseEntity.ok().body(new DataResponse(interviewService.selectInterviewInfoList(id)));
    }

    /**
     * @????????? ????????? ??????
     * @param id ??????id
     * @param request interviewInfoId, consultnantId
     * }
     * @return
     */
    @PostMapping("/{member-id}/feedback")
    public ResponseEntity<? extends MessageResponse> requestFeedback(@PathVariable("member-id") Long id, @RequestBody RequestFeedbackRequest request){
        interviewService.requestFeedback(id, request);

        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    //    - ???????????????
    //    ?????? ?????? - ??????
    @DeleteMapping("/{member-id}/{interview-info-id}")
    public ResponseEntity<? extends MessageResponse> deleteInterview(@PathVariable("member-id") Long id, @PathVariable("interview-info-id") Long interviewInfoId){


        List<InterviewVideo> findInterviewVideos = interviewService.selectS3VideoNameList(interviewInfoId);



        //s3??? ????????????
        s3Service.deleteFileS3(findInterviewVideos);


        // gcs ????????????
        try {
            gcsService.deleteFileGcs(findInterviewVideos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // db??? ????????????
        interviewService.deleteInterview(id, interviewInfoId);
        return ResponseEntity.ok().body(new MessageResponse<>());

    }




    @PostMapping("/customQuestion/create")
    public ResponseEntity createCustomInterviewQuestion(@RequestBody CustomQuestionRequest request) {
        interviewService.createCustomInterviewQuestion(request);
        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    //????????? ?????? ??????
    @DeleteMapping("/customQuestion/delete/{custom-interview-question-id}")
    public ResponseEntity deleteCustomInterviewQuestion(@PathVariable("custom-interview-question-id") Long id) {
        interviewService.deleteCustomInterviewQuestion(id);
        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    //?????? ??????
    @GetMapping("/customQuestion")
    public Optional<CustomInterviewQuestion> getCustomQuestion(@RequestParam Long id) {
        return interviewService.getCustomQuestion(id);
    }

    //????????? ?????? ?????? ??????
    @GetMapping("/customQuestion/list/{member-id}")
    public ResponseEntity<?extends DataResponse> getCustomInterviewQuestionList(@PathVariable("member-id")Long id) {
        return ResponseEntity.ok().body(new DataResponse(interviewService.getCustomInterviewQuestionList(id)));
    }


    //?????? ??????
    @GetMapping("/question")
    public Optional<InterviewQuestion> getQuestion(@RequestParam Long id) {
        return interviewService.getQuestion(id);
    }

    //?????? ?????? ??????
    @GetMapping("/question/list")
    public List<InterviewQuestion> getQuestionList(@RequestParam String questionType, @RequestParam String questionDetailType) {
        List<InterviewQuestion> questionList = interviewService.getQuestionList(questionType, questionDetailType);
        return questionList;
    }

    //?????? ?????? ??????
    @GetMapping("/question/random")
    public InterviewQuestion getRandomQuestion(@RequestParam String questionType, @RequestParam String questionDetailType) {
        return interviewService.getRandomQuestion(questionType, questionDetailType);
    }
}
