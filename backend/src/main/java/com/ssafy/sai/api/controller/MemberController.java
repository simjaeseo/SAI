package com.ssafy.sai.api.controller;

import com.ssafy.sai.api.service.MemberService;
import com.ssafy.sai.db.dto.MemberSignUpDto;
import com.ssafy.sai.db.entity.Member;
import com.ssafy.sai.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody MemberSignUpDto memberSignUpDto) {
        Long memberId = memberService.signUp(memberSignUpDto);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

}