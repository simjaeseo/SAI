package com.ssafy.sai.domain.schedule.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.sai.domain.member.domain.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static javax.persistence.FetchType.*;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduleDate;

    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private String category;

    private String detail;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "student_id")
    private Member memberStudent;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "consultant_id")
    private Member memberConsultant;

    public void addMemberStudent(Member member) {
        this.memberStudent = member;
    }

    public void addMemberConsultant(Member member) {
        this.memberConsultant = member;
    }

}
