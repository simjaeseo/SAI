package com.ssafy.sai.domain.schedule.service;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import com.ssafy.sai.domain.member.dto.ConsultantAllByCampusResponse;
import com.ssafy.sai.domain.member.repository.CampusRepository;
import com.ssafy.sai.domain.member.repository.MemberRepository;
import com.ssafy.sai.domain.schedule.domain.Schedule;
import com.ssafy.sai.domain.schedule.dto.*;
import com.ssafy.sai.domain.schedule.repository.ScheduleRepository;
import com.ssafy.sai.global.common.DataResponse;
import com.ssafy.sai.global.common.DataResponse2;
import com.ssafy.sai.global.common.MessageResponse;
import com.ssafy.sai.global.common.MessageResponse2;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.ssafy.sai.domain.member.domain.MemberStatus.TRAINEE;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final CampusRepository campusRepository;

    public DataResponse2<?> selectAll(Long id){
        // 날짜 정렬?

        Optional<Member> findMember = memberRepository.findById(id);
        if(findMember.get().getMemberStatus().equals(TRAINEE)){
            List<ScheduleAllByStudentResponse> data = scheduleRepository.selectAllByStudent(id);
            return new DataResponse2<>(200, "일정 전체 조회 성공", data);
        }else{
            List<ScheduleAllByConsultantResponse> data = scheduleRepository.selectAllByConsultant(id);
            return new DataResponse2<>(200, "일정 전체 조회 성공", data);
        }


    }

    public MessageResponse2 insert(Long id, ScheduleCreateRequest request){

        Schedule schedule = request.toEntity();

        // 교육생 or 컨설턴트 객체 가져오기
        Optional<Member> findMember = memberRepository.findById(id);

        // null 로직 나누기 -> 교육생
        if(request.getMemberConsultantId() == null){
            // 교육생 개인 일정 추가하는 경우

            // 교육생 객체를 엔티티에 추가
            schedule.addMemberStudent(findMember.get());
        }else if(request.getMemberStudentId() == null){
            // 컨설턴트 개인 일정 추가하는 경우

            // 엔티티에 교육생 객체 추가
            schedule.addMemberConsultant(findMember.get());
        }else{
            // 교육생 면접 일정 추가하는 경우

            // 컨설턴트 객체 가져오기
            Optional<Member> findMemberConsultant = memberRepository.findById(request.getMemberConsultantId());

            // 교육생 객체를 엔티티에 추가
            schedule.addMemberStudent(findMember.get());
            // 엔티티에 컨설턴트 객체 추가
            schedule.addMemberConsultant(findMemberConsultant.get());

        }

        scheduleRepository.save(schedule);

        return new MessageResponse2(200, "일정 추가 성공");
    }

    public MessageResponse2 remove(Long memberId, Long scheduleId){

        // 삭제하고자하는 일정 객체를 생성
        Optional<Schedule> findSchedule = scheduleRepository.findById(scheduleId);

        // 예외 처리 해주기

        scheduleRepository.delete(findSchedule.get());

        return new MessageResponse2(200, "일정 삭제 성공");
    }


    //테스트해야함
    public DataResponse2<List<ConsultantAllByCampusResponse>> selectConsultants(Long id){

        // 아이디를 통해서 멤버객체 가져온후
        Optional<Member> findMember = memberRepository.findById(id);



        // 해당 멤버의 캠퍼스 식별키를 통해
        List<ConsultantAllByCampusResponse> findConsultant = memberRepository.findConsultantByCampus(findMember.get().getCampus().getCity(), MemberStatus.CONSULTANT);

        return new DataResponse2<>(200,"담당 컨설턴트 조회 성공",findConsultant );
    }




    // 최근 일정 5개 받아오기!
    public DataResponse2<?> selectScheduleSinceToday(Long id){

        Optional<Member> findMember = memberRepository.findById(id);
        if(findMember.get().getMemberStatus().equals(TRAINEE)){
            List<ScheduleSinceTodayByStudentResponse> data = scheduleRepository.selectScheduleSinceTodayByStudent(id, LocalDate.now(), PageRequest.of(0,5));
            return new DataResponse2<>(200, "특정 날짜 일정 조회", data);
        }else{
            List<ScheduleSinceTodayByConsultantResponse> data = scheduleRepository.selectScheduleSinceTodayByConsultant(id, LocalDate.now(), PageRequest.of(0,5));
            return new DataResponse2<>(200, "특정 날짜 일정 조회", data);
        }


    }

    // 특정 날짜 조회
    public DataResponse2<?> selectScheduleDay(Long id, LocalDate date){

        Optional<Member> findMember = memberRepository.findById(id);
        if(findMember.get().getMemberStatus().equals(TRAINEE)){
            List<ScheduleOnSpecificDayByStudentResponse> data = scheduleRepository.selectScheduleOnSpecificDayByStudent(id, date);
            return new DataResponse2<>(200, "특정 날짜 일정 조회", data);
        }else{
            List<ScheduleOnSpecificDayByConsultantResponse> data = scheduleRepository.selectScheduleOnSpecificDayByConsultant(id, date);
            return new DataResponse2<>(200, "특정 날짜 일정 조회", data);
        }

    }

}
