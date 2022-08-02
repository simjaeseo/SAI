package com.ssafy.sai.domain.member.dto.request;

import com.ssafy.sai.domain.job.dto.EnterpriseName;
import com.ssafy.sai.domain.job.dto.JobName;
import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.dto.CampusDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MemberUpdateRequest {

    @NotNull(message = "캠퍼스 정보는 필수 입력값입니다.")
    private CampusDto campus;

    private String profilePicture;
    private String phone;

    private List<EnterpriseName> interestedEnterprises;
    private List<JobName> interestedJobs;
}
