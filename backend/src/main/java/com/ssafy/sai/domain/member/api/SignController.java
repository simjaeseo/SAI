package com.ssafy.sai.domain.member.api;

import javax.validation.Valid;

import com.ssafy.sai.domain.member.dto.request.ConsultantSignUpRequest;
import com.ssafy.sai.domain.member.dto.request.MemberLoginRequest;
import com.ssafy.sai.domain.member.dto.request.MemberSignUpRequest;
import com.ssafy.sai.domain.member.dto.response.AuthenticationConDto;
import com.ssafy.sai.domain.member.dto.response.AuthenticationDto;
import com.ssafy.sai.domain.member.service.SignService;
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
     * @param request
     * @throws Exception
     * @method 설명 : 교육생 회원가입
     */
    @PostMapping(value = {"signup/member"})
    public ResponseEntity<AuthenticationDto> signUpMember(
            @Valid @RequestBody MemberSignUpRequest request) throws Exception {

        return ResponseEntity.ok()
                .body(signService.signUpMember(request));
    }

    /**
     * @param request
     * @throws Exception
     * @method 설명 : 컨설턴트 회원가입
     */
    @PostMapping(value = {"signup/consultant"})
    public ResponseEntity<AuthenticationConDto> signUpConsultant(
            @Valid @RequestBody ConsultantSignUpRequest request) throws Exception {

        return ResponseEntity.ok()
                .body(signService.signUpConsultant(request));
    }

    /**
     * @param request
     * @throws Exception
     * @method 설명 : 로그인
     */
    @PostMapping(value = {"/login"})
    public ResponseEntity<AuthenticationDto> login(
            @Valid @RequestBody MemberLoginRequest request) throws Exception {

        AuthenticationDto authentication = signService.loginMember(request);

        return ResponseEntity.ok()
                .header("accesstoken", authProvider
                        .createToken(
                                authentication.getId(),
                                authentication.getEmail(),
                                "USER"))
                .body(authentication);
    }
}