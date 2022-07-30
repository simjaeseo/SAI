package com.ssafy.sai.domain.member.api;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.dto.ConsultantSignUpRequest;
import com.ssafy.sai.domain.member.dto.MemberDto;
import com.ssafy.sai.domain.member.dto.MemberSignUpRequest;
import com.ssafy.sai.domain.member.dto.MemberUpdateRequest;
import com.ssafy.sai.domain.member.service.MemberService;
import com.ssafy.sai.global.common.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DataResponse<MemberDto> findMemberInfo(@PathVariable Long id) {
        Member findMember = memberService.findMember(id);
        MemberDto memberDto = findMember.toMemberDto();
        return new DataResponse<>(200, "OK", memberDto);
    }

    @PostMapping("/member")
    @ResponseStatus(HttpStatus.OK)
    public Long singUpMember(@RequestBody @Valid MemberSignUpRequest request) throws Exception {
        return memberService.signUpMember(request);
    }

    @PostMapping("/consultant")
    @ResponseStatus(HttpStatus.OK)
    public Long singUpConsultant(@RequestBody @Valid ConsultantSignUpRequest request) throws Exception {
        return memberService.signUpConsultant(request);
    }

    @PutMapping("/{id}")
    public void updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberUpdateRequest request) throws Exception {
        memberService.updateMember(id, request);
    }
}