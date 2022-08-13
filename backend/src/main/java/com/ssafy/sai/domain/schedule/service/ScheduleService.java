package com.ssafy.sai.domain.schedule.service;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import com.ssafy.sai.domain.member.dto.response.ConsultantAllByCampusResponse;
import com.ssafy.sai.domain.member.exception.MemberException;
import com.ssafy.sai.domain.member.exception.MemberExceptionType;
import com.ssafy.sai.domain.member.repository.MemberRepository;
import com.ssafy.sai.domain.schedule.domain.Schedule;
import com.ssafy.sai.domain.schedule.dto.request.ScheduleCreateRequest;
import com.ssafy.sai.domain.schedule.dto.response.*;
import com.ssafy.sai.domain.schedule.exception.ScheduleException;
import com.ssafy.sai.domain.schedule.exception.ScheduleExceptionType;
import com.ssafy.sai.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ssafy.sai.domain.member.domain.MemberStatus.TRAINEE;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;


    /**
     * @param id 멤버 PK
     * @return 교육생일때->일정정보, (컨설턴트 이름) / 컨설턴트일때->일정정보, (교육생 이름)
     * @throws MemberException 멤버가 존재하지 않을때 예외 발생
     * @메소드 전체 일정 조회 서비스
     */
    public List<?> selectAll(Long id) throws MemberException {

        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "scheduleDate", "startTime"));

        if (findMember.getMemberStatus().equals(TRAINEE)) {
            Page<Schedule> schedules = scheduleRepository.selectAllByStudent(id, pageRequest);
            Page<ScheduleAllByStudentResponse> page = schedules.map(schedule -> new ScheduleAllByStudentResponse(schedule));
            List<ScheduleAllByStudentResponse> result = page.getContent();

            return result;
        }

        Page<Schedule> schedules = scheduleRepository.selectAllByConsultant(id, pageRequest);
        Page<ScheduleAllByConsultantResponse> page = schedules.map(schedule -> new ScheduleAllByConsultantResponse(schedule));
        List<ScheduleAllByConsultantResponse> result = page.getContent();

        return result;
    }


    /**
     * @param id      멤버 PK
     * @param request (오늘 날짜 (yyyy-MM-dd), 일정 시작 시간 (HH:mm), 일정 종료 시간 (HH:mm) , 분류, 상세정보, 교육생 PK, 컨설턴트 PK)
     * @throws MemberException 멤버가 존재하지 않을때 예외 발생
     * @메소드 일정 추가 서비스
     */
    public void insert(Long id, ScheduleCreateRequest request) throws MemberException {

        Schedule schedule = request.toEntity();

        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        if (request.getMemberConsultantId() == null) {
            schedule.addMemberStudent(findMember);
        } else if (request.getMemberStudentId() == null) {
            schedule.addMemberConsultant(findMember);
        } else {
            Optional<Member> findMemberConsultant = memberRepository.findById(request.getMemberConsultantId());
            schedule.addMemberStudent(findMember);
            schedule.addMemberConsultant(findMemberConsultant.get());
        }

        scheduleRepository.save(schedule);
    }

    /**
     * @param memberId   멤버 PK
     * @param scheduleId 일정 PK
     * @throws ScheduleException 일정이 존재하지 않을때 예외 발생
     * @메소드 일정 삭제 서비스
     */
    public void remove(Long memberId, Long scheduleId) throws ScheduleException {

        Schedule findSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleException(ScheduleExceptionType.NOT_FOUND_SCHEDULE));

        scheduleRepository.delete(findSchedule);
    }


    /**
     * @param id 멤버 PK
     * @return 조회한 컨설턴트 id, 이름
     * @throws MemberException 멤버가 존재하지 않을때 예외 발생
     * @메소드 담당 컨설턴트 조회 서비스
     */
    public List<ConsultantAllByCampusResponse> selectConsultants(Long id) throws MemberException {

        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        List<Member> findConsultant
                = memberRepository.findConsultantByCampus(findMember.getCampus().getCity(), MemberStatus.CONSULTANT);

        List<ConsultantAllByCampusResponse> result = findConsultant.stream()
                .map(consultant -> new ConsultantAllByCampusResponse(consultant))
                .collect(Collectors.toList());

        return result;
    }


    /**
     * @param id 멤버의 PK
     * @return 일정 정보 + 멤버 PK, 멤버 이름
     * @throws MemberException 멤버가 존재하지 않을때 예외 발생
     * @메소드 현재 시간 기준으로 앞으로 다가올 일정 조회 서비스 (최대 5개)
     */
    public List<?> selectScheduleSinceToday(Long id) throws MemberException {

        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "scheduleDate", "startTime"));

        if (findMember.getMemberStatus().equals(TRAINEE)) {
            Page<Schedule> schedules = scheduleRepository.selectScheduleSinceTodayByStudent(id, LocalDate.now(), LocalTime.now(), pageRequest);
            Page<ScheduleSinceTodayByStudentResponse> page = schedules.map(schedule -> new ScheduleSinceTodayByStudentResponse(schedule));
            List<ScheduleSinceTodayByStudentResponse> result = page.getContent();

            return result;
        }

        Page<Schedule> schedules = scheduleRepository.selectScheduleSinceTodayByConsultant(id, LocalDate.now(), LocalTime.now(), pageRequest);
        Page<ScheduleSinceTodayByConsultantResponse> page = schedules.map(schedule -> new ScheduleSinceTodayByConsultantResponse(schedule));
        List<ScheduleSinceTodayByConsultantResponse> result = page.getContent();

        return result;
    }


    /**
     * @param id   멤버의 PK
     * @param date 특정 날짜 (yyyy-MM-dd)
     * @return 특정 날짜의 일정 정보
     * @throws MemberException 멤버가 존재하지 않을때 예외 발생
     * @메소드 특정 멤버의 특정 날짜 일정을 조회하는 api
     */
    public List<?> selectScheduleDay(Long id, LocalDate date) throws MemberException {

        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        if (findMember.getMemberStatus().equals(TRAINEE)) {
            List<Schedule> schedules = scheduleRepository.selectScheduleOnSpecificDayByStudent(id, date);
            List<ScheduleOnSpecificDayByStudentResponse> data = schedules.stream()
                    .map(schedule -> new ScheduleOnSpecificDayByStudentResponse(schedule))
                    .collect(Collectors.toList());

            return data;
        }

        List<Schedule> schedules = scheduleRepository.selectScheduleOnSpecificDayByConsultant(id, date);
        List<ScheduleOnSpecificDayByConsultantResponse> data = schedules.stream()
                .map(schedule -> new ScheduleOnSpecificDayByConsultantResponse(schedule))
                .collect(Collectors.toList());
        return data;
    }

}
