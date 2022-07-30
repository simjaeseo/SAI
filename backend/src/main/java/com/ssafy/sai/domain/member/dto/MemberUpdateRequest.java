package com.ssafy.sai.domain.member.dto;

import com.ssafy.sai.domain.job.domain.InterestedEnterprise;
import com.ssafy.sai.domain.job.domain.InterestedJob;
import com.ssafy.sai.domain.job.dto.EnterpriseId;
import com.ssafy.sai.domain.job.dto.JobId;
import com.ssafy.sai.domain.member.domain.Campus;
import com.ssafy.sai.domain.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class MemberUpdateRequest {

    private Long id;

    @NotNull(message = "캠퍼스 정보는 필수 입력값입니다.")
    private CampusDto campus;

    @NotBlank(message = "연락처는 필수 입력값입니다.")
    private String phone;

    private String profilePicture;

    private List<EnterpriseId> interestedEnterprises;
    private List<JobId> interestedJobs;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .phone(phone)
                .profilePicture(profilePicture)
                .build();
    }
}
