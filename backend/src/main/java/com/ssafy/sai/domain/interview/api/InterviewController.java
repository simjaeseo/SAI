package com.ssafy.sai.domain.interview.api;

import com.ssafy.sai.domain.interview.domain.CustomInterviewQuestion;
import com.ssafy.sai.domain.interview.domain.InterviewQuestion;
import com.ssafy.sai.domain.interview.dto.DeleteInterviewVideoRequest;
import com.ssafy.sai.domain.interview.dto.request.CustomQuestionRequest;
import com.ssafy.sai.domain.interview.service.GcsService;
import com.ssafy.sai.domain.interview.service.InterviewService;
import com.ssafy.sai.domain.interview.service.S3UploaderService;
import com.ssafy.sai.domain.interview.service.STTSample;
import com.ssafy.sai.global.common.DataResponse;
import com.ssafy.sai.global.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;
    private final S3UploaderService s3UploaderService;
    private final STTSample sttSample;
    private final GcsService gcsService;

    @PostMapping("/{member_id}/video")
    public ResponseEntity<? extends MessageResponse> createInterviewVideo(@PathVariable("member_id") Long id,  @RequestParam("video") MultipartFile multipartFile) {
        // 쿼리 말고 body로 보내라고 하자!!!!!
        // 요청올때 면접 영상 뿐만 아니라 면접 정보도 같이 오겠지?? 그럼 body로 받아야함!

        // 서비스에서 면접정보 먼저 저장하고 -> 면접 영상 저장하는 방식으로 해야될거같다

        return ResponseEntity.ok().body(new DataResponse<>(s3UploaderService.uploadFileS3(id, multipartFile)));

    }

    /**
     * Amazon S3에 업로드 된 파일을 삭제
     * @return 성공 시 200 Success
     */
    @DeleteMapping("/{member_id}/video")
    public ResponseEntity deleteInterviewVideo(@PathVariable("member_id") Long memberId, @RequestBody DeleteInterviewVideoRequest request) {
        // 쿼리 말고 body로 보내라고 하자!!!!! s3 파일 이름때문에 url이 너무 길어질듯..!

        s3UploaderService.deleteFileS3(memberId, request);
        return new ResponseEntity<>(new MessageResponse<>(), HttpStatus.OK);
    }


    @PostMapping("/{member_id}/audio")
    public ResponseEntity<? extends MessageResponse> createInterviewAudio(@PathVariable("member_id") Long id,  @RequestParam("audio") MultipartFile multipartFile) {
        // 쿼리 말고 body로 보내라고 하자!!!!!
        // 요청올때 면접 영상 뿐만 아니라 면접 정보도 같이 오겠지?? 그럼 body로 받아야함!

        // 서비스에서 면접정보 먼저 저장하고 -> 면접 영상 저장하는 방식으로 해야될거같다
        try {
            gcsService.uploadFileGcs(id, multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(new MessageResponse<>());

    }
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

    //질문 조회
    @GetMapping("/customQuestion")
    public Optional<CustomInterviewQuestion> getCustomQuestion(@RequestParam Long id){
        return interviewService.getCustomQuestion(id);
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
