package com.ssafy.sai.domain.member.api;

import javax.validation.Valid;

import com.ssafy.sai.domain.member.dto.AuthenticationMember;
import com.ssafy.sai.domain.member.dto.request.ConsultantSignUpRequest;
import com.ssafy.sai.domain.member.dto.request.MemberLoginRequest;
import com.ssafy.sai.domain.member.dto.request.MemberSignUpRequest;
import com.ssafy.sai.domain.member.dto.response.MemberResponse;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.service.SignService;
import com.ssafy.sai.global.common.MessageResponse;
import com.ssafy.sai.global.util.auth.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
public class SignController {

    private final SignService signService;

    private final AuthProvider authProvider;

    /**
     * @param request 교육생 회원가입 폼 양식
     * @throws Exception 필수 입력값 미입력, 이미 존재하는 이메일 또는 휴대전화번호 입력시 예외 발생
     * @메소드 교육생 회원가입 컨트롤러
     */
    @PostMapping(value = {"signup/member"})
    public ResponseEntity<? extends MessageResponse> signUpMember(
            @Valid @RequestBody MemberSignUpRequest request) {
        MemberResponse findMember = signService.signUpMember(request);
        return ResponseEntity.ok()
                .body(new MessageResponse<>(findMember));
    }

    /**
     * @param request 컨설턴트 회원가입 폼 양식
     * @throws Exception 필수 입력값 미입력, 이미 존재하는 이메일 또는 휴대전화번호 입력시 예외 발생
     * @메소드 컨설턴트 회원가입 컨트롤러
     */
    @PostMapping(value = {"signup/consultant"})
    public ResponseEntity<? extends MessageResponse> signUpConsultant(
            @Valid @RequestBody ConsultantSignUpRequest request) {
        MemberResponse findMember = signService.signUpConsultant(request);
        return ResponseEntity.ok()
                .body(new MessageResponse<>(findMember));
    }


    /**
     * @param request 로그인 폼 양식(email, password)
     * @return 로그인한 회원의 정보
     * @throws Exception 아이디 불일치, 비밀번호 불일치시 예외 발생
     * @메소드 회원 로그인 컨트롤러
     */
    @RequestMapping(value = {"/login"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<? extends MessageResponse> login(
            @Valid @RequestBody MemberLoginRequest request) {
        AuthenticationMember authentication = signService.loginMember(request);

        if (authentication == null) {
            throw new MemberException(MemberExceptionType.NOT_FOUND_MEMBER);
        }

        return ResponseEntity.ok()
                .header("accesstoken", authProvider
                        .createToken(
                                authentication.getId(),
                                authentication.getEmail(),
                                "USER"))
                .body(new MessageResponse<>(authentication));
    }
}