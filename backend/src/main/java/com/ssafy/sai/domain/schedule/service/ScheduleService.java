package com.ssafy.sai.domain.schedule.service;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import com.ssafy.sai.domain.member.dto.ConsultantAllByCampusResponse;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.repository.MemberRepository;
import com.ssafy.sai.domain.schedule.domain.Schedule;
import com.ssafy.sai.domain.schedule.dto.*;
import com.ssafy.sai.domain.schedule.exception.ScheduleException;
import com.ssafy.sai.domain.schedule.exception.ScheduleExceptionType;
import com.ssafy.sai.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.ssafy.sai.domain.member.domain.MemberStatus.TRAINEE;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;


    /**
     * @메소드 전체 일정 조회 서비스
     * @param id 멤버 PK
     * @return 교육생일때->일정정보, (컨설턴트 이름) / 컨설턴트일때->일정정보, (교육생 이름)
     * @throws MemberException 멤버가 존재하지 않을때 예외 발생
     */
    public List<?> selectAll(Long id) throws MemberException{
        // 날짜 정렬?

        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
        if(findMember.getMemberStatus().equals(TRAINEE)){
            List<ScheduleAllByStudentResponse> data = scheduleRepository.selectAllByStudent(id);
            return data;
        }else{
            List<ScheduleAllByConsultantResponse> data = scheduleRepository.selectAllByConsultant(id);
            return data;
        }

    }


    /**
     * @메소드 일정 추가 서비스
     * @param id 멤버 PK
     * @param request (오늘 날짜 (yyyy-MM-dd), 일정 시작 시간 (HH:mm), 일정 종료 시간 (HH:mm) , 분류, 상세정보, 교육생 PK, 컨설턴트 PK)
     * @throws MemberException 멤버가 존재하지 않을때 예외 발생
     */
    public void insert(Long id, ScheduleCreateRequest request) throws MemberException{

        Schedule schedule = request.toEntity();

        // 교육생 or 컨설턴트 객체 가져오기
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));


        // null 로직 나누기 -> 교육생
        if(request.getMemberConsultantId() == null){
            // 교육생 개인 일정 추가하는 경우

            // 교육생 객체를 엔티티에 추가
            schedule.addMemberStudent(findMember);
        }else if(request.getMemberStudentId() == null){
            // 컨설턴트 개인 일정 추가하는 경우

            // 엔티티에 교육생 객체 추가
            schedule.addMemberConsultant(findMember);
        }else{
            // 교육생 면접 일정 추가하는 경우

            // 컨설턴트 객체 가져오기
            Optional<Member> findMemberConsultant = memberRepository.findById(request.getMemberConsultantId());

            // 교육생 객체를 엔티티에 추가
            schedule.addMemberStudent(findMember);
            // 엔티티에 컨설턴트 객체 추가
            schedule.addMemberConsultant(findMemberConsultant.get());

        }

        scheduleRepository.save(schedule);

    }

    /**
     * @메소드 일정 삭제 서비스
     * @param memberId 멤버 PK
     * @param scheduleId 일정 PK
     * @throws ScheduleException 일정이 존재하지 않을때 예외 발생
     */
    public void remove(Long memberId, Long scheduleId) throws ScheduleException{

        // 삭제하고자하는 일정 객체를 생성
        Schedule findSchedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ScheduleException(ScheduleExceptionType.NOT_FOUND_SCHEDULE));


        scheduleRepository.delete(findSchedule);

    }


    /**
     * @메소드 담당 컨설턴트 조회 서비스
     * @param id 멤버 PK
     * @return 조회한 컨설턴트 id, 이름
     * @throws MemberException 멤버가 존재하지 않을때 예외 발생
     */
    public List<ConsultantAllByCampusResponse> selectConsultants(Long id) throws MemberException{

        // 아이디를 통해서 멤버객체 가져온후
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));



        // 해당 멤버의 캠퍼스 식별키를 통해
        List<ConsultantAllByCampusResponse> findConsultant = memberRepository.findConsultantByCampus(findMember.getCampus().getCity(), MemberStatus.CONSULTANT);

        return findConsultant;
    }


    /**
     * @메소드 현재 시간 기준으로 앞으로 다가올 일정 조회 서비스 (최대 5개)
     * @param id 멤버의 PK
     * @return 일정 정보 + 멤버 PK, 멤버 이름
     * @throws MemberException 멤버가 존재하지 않을때 예외 발생
     */
    public List<?> selectScheduleSinceToday(Long id) throws MemberException{

        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        if(findMember.getMemberStatus().equals(TRAINEE)){
            List<ScheduleSinceTodayByStudentResponse> data = scheduleRepository.selectScheduleSinceTodayByStudent(id, LocalDate.now(), LocalTime.now(), PageRequest.of(0,5));
            return data;
        }else{
            List<ScheduleSinceTodayByConsultantResponse> data = scheduleRepository.selectScheduleSinceTodayByConsultant(id, LocalDate.now(), LocalTime.now(), PageRequest.of(0,5));
            return data;
        }

    }

    /**
     * @메소드 특정 멤버의 특정 날짜 일정을 조회하는 api
     * @param id 멤버의 PK
     * @param date 특정 날짜 (yyyy-MM-dd)
     * @return 특정 날짜의 일정 정보
     * @throws MemberException 멤버가 존재하지 않을때 예외 발생
     */
    public List<?> selectScheduleDay(Long id, LocalDate date) throws MemberException{

        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        if(findMember.getMemberStatus().equals(TRAINEE)){
            List<ScheduleOnSpecificDayByStudentResponse> data = scheduleRepository.selectScheduleOnSpecificDayByStudent(id, date);
            return data;
        }else{
            List<ScheduleOnSpecificDayByConsultantResponse> data = scheduleRepository.selectScheduleOnSpecificDayByConsultant(id, date);
            return data;
        }

    }

}
