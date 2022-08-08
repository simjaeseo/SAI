package com.ssafy.sai.domain.member.api;

import com.ssafy.sai.domain.member.domain.ProfilePicture;
import com.ssafy.sai.domain.member.dto.*;
import com.ssafy.sai.domain.member.dto.request.ConsultantUpdateRequest;
import com.ssafy.sai.domain.member.dto.request.FindIdRequest;
import com.ssafy.sai.domain.member.dto.request.MemberUpdateRequest;
import com.ssafy.sai.domain.member.dto.response.MemberResponse;
import com.ssafy.sai.domain.member.service.MemberService;
import com.ssafy.sai.global.common.DataResponse;
import com.ssafy.sai.global.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
@CrossOrigin("*")
public class MemberController {

    private final MemberService memberService;

    /**
     * @param id 조회할 교육생의 PK
     * @return 조회한 교육생의 정보
     * @throws Exception 잘못된 접근일 때 예외 발생
     * @메소드 교육생 정보 조회 컨트롤러
     */
    @GetMapping("/member/{id}")
    public ResponseEntity<? extends DataResponse> findMember(@PathVariable Long id) {
        return ResponseEntity.ok().body(new DataResponse<>(memberService.findMemberOne(id)));
    }

    /**
     * @param id 조회할 컨설턴트의 PK
     * @return 조회한 컨설턴트의 정보
     * @throws Exception 잘못된 접근일 때 예외 발생
     * @메소드 컨설턴트 정보 조회 컨트롤러
     */
    @GetMapping("consultant/{id}")
    public ResponseEntity<? extends DataResponse> findConsultant(@PathVariable Long id) {
        return ResponseEntity.ok().body(new DataResponse<>(memberService.findConsultantOne(id)));
    }

    /**
     * @param file    회원 프로필 이미지 파일
     * @param id      정보를 수정할 회원의 PK
     * @param request 회원 정보 수정 폼 양식
     * @return 변경한 회원의 이메일과 이름
     * @throws Exception 잘못된 접근, 이미 존재하는 휴대전화 번호로 변겅할 때 예외 발생
     * @메소드 교육생 정보 수정 컨트롤러
     */
    @PutMapping("/member/{id}")
    public ResponseEntity<? extends DataResponse> updateMember(@RequestPart(value = "file", required = false) MultipartFile file, @PathVariable("id") Long id, @RequestPart("request") @Valid MemberUpdateRequest request) {
        return ResponseEntity.ok().body(new DataResponse<>(memberService.updateMember(file, id, request)));
    }

    /**
     * @param file    회원 프로필 이미지 파일
     * @param id      정보를 수정할 회원의 PK
     * @param request 회원 정보 수정 폼 양식
     * @return 변경한 회원의 이메일과 이름
     * @메소드 컨설턴트 정보 수정 컨트롤러
     */
    @PutMapping("consultant/{id}")
    public ResponseEntity<? extends DataResponse> updateConsultant(@RequestPart(value = "file", required = false) MultipartFile file, @PathVariable("id") Long id, @RequestPart("request") @Valid ConsultantUpdateRequest request) {
        return ResponseEntity.ok().body(new DataResponse<>(memberService.updateConsultant(file, id, request)));
    }

    /**
     * @param passwordDto 비밀번호 변경 폼 양식(old password, new password, new password check)
     * @return 변경한 회원의 이메일과 이름
     * @throws Exception 잘못된 접근일 때 예외 발생
     * @메소드 회원 비밀번호 변경 컨트롤러
     */
    @PostMapping("/password")
    public ResponseEntity updatePassword(@Valid @RequestBody PasswordDto passwordDto) {
        if (memberService.updatePassword(passwordDto) == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(new MessageResponse<>());
    }

    /**
     * @param id 프로필 사진 PK
     * @return 이미지 출력
     * @throws IOException 입출력 오류시 예외 발생
     * @메소드 이미지 출력 컨트롤러
     */
    @GetMapping(value = "/{id}/profile", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public String getImage(@PathVariable Long id) throws IOException {
        Map<String, Object> map = memberService.getImage(id);
        ProfilePicture profilePicture = (ProfilePicture) map.get("image");
        Object fileDir = map.get("path");
        String path = profilePicture.getFileName();

        return path;
    }

    /**
     * @param request 아이디 찾기 폼 양식
     * @return 조회된 회원의 이름과 이메일
     * @메소드 회원 아이디 찾기 컨트롤러
     */
    @PostMapping
    public ResponseEntity<? extends DataResponse> findMemberId(@Valid @RequestBody FindIdRequest request) {
        MemberResponse findMember = memberService.findMemberId(request);
        return ResponseEntity.ok().body(new DataResponse<>(findMember));
    }

}