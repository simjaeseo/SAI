package com.ssafy.sai.domain.schedule.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.sai.domain.member.domain.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name="schedule_date")
    private LocalDate scheduleDate;

    @DateTimeFormat(pattern = "hh:mm")
    @Column(name="start_time")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "hh:mm")
    @Column(name="end_time")
    private LocalTime endTime;

    private String category;

    private String detail;

    @JsonBackReference
    @ManyToOne(fetch =   FetchType.EAGER)
    @JoinColumn(name="member_student_id")
    private Member memberStudent;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="member_consultant_id")
    private Member memberConsultant;

    public void addMemberStudent(Member member){
        this.memberStudent = member;
    }

    public void addMemberConsultant(Member member){
        this.memberConsultant = member;
    }

}
