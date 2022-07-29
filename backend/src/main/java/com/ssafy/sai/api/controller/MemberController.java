package com.ssafy.sai.api.controller;

import com.ssafy.sai.api.request.MemberSignUpRequest;
import com.ssafy.sai.api.request.MemberUpdateRequest;
import com.ssafy.sai.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void singUpMember(@RequestBody @Valid MemberSignUpRequest request) throws Exception {
        memberService.signUpMember(request);
    }

    @PutMapping("/{id}")
    public void updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberUpdateRequest request) throws Exception {
        memberService.updateMember(id, request);
    }
}