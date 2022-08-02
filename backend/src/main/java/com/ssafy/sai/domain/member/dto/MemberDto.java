package com.ssafy.sai.domain.member.dto;

import com.ssafy.sai.domain.job.domain.InterestedEnterprise;
import com.ssafy.sai.domain.job.domain.InterestedJob;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.MemberStatus;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberDto {

    private Long id;
    private String email;
    private String name;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthday;
    private MemberStatus memberStatus;
    private int year;
    private CampusDto campus;
    private String phone;

    private List<InterestedJobDto> interestedJobs;
    private List<InterestedEnterpriseDto> interestedEnterprises;

    public MemberDto(Member member) {
        id = member.getId();
        email = member.getEmail();
        name = member.getName();
        birthday = member.getBirthday();
        memberStatus = member.getMemberStatus();
        year = member.getYear();
        campus = new CampusDto(member.getCampus().getCity(), member.getCampus().getClassNumber());
        phone = member.getPhone();
        interestedJobs = member.getInterestedJobs().stream()
                .map(interestedJob -> new InterestedJobDto(interestedJob))
                .collect(Collectors.toList());
        interestedEnterprises = member.getInterestedEnterprises().stream()
                .map(interestedEnterprise -> new InterestedEnterpriseDto(interestedEnterprise))
                .collect(Collectors.toList());
    }

    @Getter
    static class InterestedJobDto {
        private String jobName;

        public InterestedJobDto(InterestedJob interestedJob) {
            this.jobName = interestedJob.getJob().getName();
        }
    }

    @Getter
    static class InterestedEnterpriseDto {
        private String enterpriseName;

        public InterestedEnterpriseDto(InterestedEnterprise interestedEnterprise) {
            this.enterpriseName = interestedEnterprise.getEnterprise().getName();
        }
    }
}
