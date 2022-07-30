package com.ssafy.sai.domain.member.api;

import com.ssafy.sai.domain.member.dto.MemberSignUpRequest;
import com.ssafy.sai.domain.member.dto.MemberUpdateRequest;
import com.ssafy.sai.domain.member.service.MemberService;
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
    public Long singUpMember(@RequestBody @Valid MemberSignUpRequest request) throws Exception {
        return memberService.signUpMember(request);
    }

    @PutMapping("/{id}")
    public void updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberUpdateRequest request) throws Exception {
        memberService.updateMember(id, request);
    }
}