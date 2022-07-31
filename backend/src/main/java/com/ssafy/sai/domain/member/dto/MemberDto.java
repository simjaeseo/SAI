package com.ssafy.sai.domain.member.dto;

import com.ssafy.sai.domain.job.dto.EnterpriseId;
import com.ssafy.sai.domain.job.dto.JobId;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class MemberDto {

    private String email;
    private String name;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthday;
    private MemberStatus memberStatus;
    private int year;
    private CampusDto campus;
    private String phone;

    private List<JobId> interestedJobs;
    private List<EnterpriseId> interestedEnterprises;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .name(name)
                .year(year)
                .campus(campus.toEntity())
                .memberStatus(memberStatus)
                .birthday(birthday)
                .phone(phone)
                .build();
    }
}
