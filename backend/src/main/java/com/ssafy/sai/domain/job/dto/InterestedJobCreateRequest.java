package com.ssafy.sai.domain.job.dto;

import com.ssafy.sai.domain.job.domain.InterestedJob;
import com.ssafy.sai.domain.job.domain.Job;
import com.ssafy.sai.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InterestedJobCreateRequest {

    @NotEmpty
    private Job job;

    @NotEmpty
    private Member member;

    public InterestedJob toEntity() {
        return InterestedJob.builder()
                .job(job)
                .member(member)
                .build();
    }

}
