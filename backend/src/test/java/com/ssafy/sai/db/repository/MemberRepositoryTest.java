package com.ssafy.sai.db.repository;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import com.ssafy.sai.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @AfterEach
    private void after() {
        em.clear();
    }

    @Test
    public void 회원저장_성공() throws Exception {
        //given
//        Member member = new Member("ssafy@ssafy.com", "싸피", "123456789", null);
        Member member = Member.builder()
                .email("ssafy@ssafy.com")
                .password("1234567890")
                .memberStatus(MemberStatus.TRAINEE)
                .campus(null)
                .phone("010-1234-1234")
                .profilePicture("www.asdfasdf.com")
                .build();
        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId())
                .orElseThrow(() -> new RuntimeException("저장된 회원이 없습니다"));

        assertThat(findMember).isSameAs(savedMember);
        assertThat(findMember).isSameAs(member);
    }

    @Test
    public void 회원가입_중복이메일_체크() throws Exception {
        //given
        Member member1 = Member.builder()
                .email("ssafy@ssafy.com")
                .password("1234567890")
                .memberStatus(MemberStatus.TRAINEE)
                .campus(null)
                .phone("010-1234-1234")
                .profilePicture("www.asdfasdf.com")
                .build();

        Member member2 = Member.builder()
                .email("ssafy@ssafy.com")
                .password("1234567890")
                .memberStatus(MemberStatus.TRAINEE)
                .campus(null)
                .phone("010-1234-1234")
                .profilePicture("www.asdfasdf.com")
                .build();

        memberRepository.save(member1);
        em.clear();

        //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member2));

    }

    @Test
    public void 회원삭제_성공() throws Exception {
        //given
        Member member = Member.builder()
                .email("ssafy@ssafy.com")
                .password("1234567890")
                .memberStatus(MemberStatus.TRAINEE)
                .campus(null)
                .phone("010-1234-1234")
                .profilePicture("www.asdfasdf.com")
                .build();

        memberRepository.save(member);
        //when
        memberRepository.delete(member);

        //then
        assertThrows(Exception.class, () -> memberRepository.findById(member.getId())
                .orElseThrow(() -> new Exception()));
    }

    @Test
    public void 회원가입시간_등록() throws Exception {
        //given
        Member member = Member.builder()
                .email("ssafy@ssafy.com")
                .password("1234567890")
                .memberStatus(MemberStatus.TRAINEE)
                .campus(null)
                .phone("010-1234-1234")
                .profilePicture("www.asdfasdf.com")
                .build();

        memberRepository.save(member);
        //when
        Member findMember = memberRepository.findById(member.getId()).orElseThrow(() -> new Exception());

        //then
        assertThat(findMember.getCreatedDate()).isNotNull();
        assertThat(findMember.getUpdatedDate()).isNotNull();
    }


}