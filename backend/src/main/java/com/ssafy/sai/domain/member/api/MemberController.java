package com.ssafy.sai.domain.member.api;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.dto.ConsultantSignUpRequest;
import com.ssafy.sai.domain.member.dto.MemberDto;
import com.ssafy.sai.domain.member.dto.MemberSignUpRequest;
import com.ssafy.sai.domain.member.dto.MemberUpdateRequest;
import com.ssafy.sai.domain.member.service.MemberService;
import com.ssafy.sai.global.common.DataResponse;
import com.ssafy.sai.global.common.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DataResponse<MemberDto> findMemberInfo(@PathVariable Long id) {
        Member findMember = memberService.findMember(id);
        MemberDto memberDto = findMember.toMemberDto();
        return new DataResponse<>(200, "OK", memberDto);
    }

    @PostMapping("/member")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse signUpMember(@RequestBody @Valid MemberSignUpRequest request) throws Exception {
        memberService.signUpMember(request);
        return new MessageResponse(200, "OK");
    }

    @PostMapping("/consultant")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse signUpConsultant(@RequestBody @Valid ConsultantSignUpRequest request) throws Exception {
        memberService.signUpConsultant(request);
        return new MessageResponse(200, "OK");
    }

    @PutMapping("/{id}")
    public MessageResponse updateMember(@PathVariable("id") Long id, @RequestBody @Valid MemberUpdateRequest request) throws Exception {
        memberService.updateMember(id, request);
        return new MessageResponse(200, "OK");
    }
}