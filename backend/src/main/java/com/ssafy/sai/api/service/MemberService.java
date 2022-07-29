package com.ssafy.sai.api.service;

import com.ssafy.sai.api.request.FavoriteEnterpriseCreateRequest;
import com.ssafy.sai.api.request.MemberSignUpRequest;
import com.ssafy.sai.api.request.MemberUpdateRequest;
import com.ssafy.sai.db.dto.EnterpriseId;
import com.ssafy.sai.db.entity.Enterprise;
import com.ssafy.sai.db.entity.FavoriteEnterprise;
import com.ssafy.sai.db.entity.Member;
import com.ssafy.sai.db.repository.EnterpriseRepository;
import com.ssafy.sai.db.repository.FavoriteEnterpriseRepository;
import com.ssafy.sai.db.repository.MemberRepository;
import com.ssafy.sai.exception.member.MemberException;
import com.ssafy.sai.exception.member.MemberExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final FavoriteEnterpriseRepository favoriteEnterpriseRepository;
    private final EnterpriseRepository enterpriseRepository;

    /*
    회원 가입
     */
    @Transactional
    public void signUpMember(MemberSignUpRequest request) throws Exception {
        Member member = request.toEntity();

        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new MemberException(MemberExceptionType.ALREADY_EXIST_EMAIL);
        }

        Member findMember = memberRepository.saveAndFlush(member);
//        // 관심기업 테이블의 기업 식별키와 멤버 식별키를 insert
        for(EnterpriseId enterpriseId : request.getFavoriteEnterprises()) {
            // 기업 테이블 조회
            Optional<Enterprise> enterprise = enterpriseRepository.findById(enterpriseId.getId());

            // enterprise.get() 인자 값 수정
            FavoriteEnterpriseCreateRequest favoriteEnterpriseCreateRequest = new FavoriteEnterpriseCreateRequest(enterprise.get(), findMember);

            FavoriteEnterprise favoriteEnterprise = favoriteEnterpriseCreateRequest.toEntity();

            favoriteEnterpriseRepository.save(favoriteEnterprise);
        }

        // 관심직무 테이블의 직무 식벼맄와 멤버 식별키를 insert
    }

    @Transactional
    public void updateMember(Long id, MemberUpdateRequest request) throws Exception {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.WRONG_MEMBER_INFORMATION));

        findMember.updateMember(request);
    }
}