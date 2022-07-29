package com.ssafy.sai.api.request;

import com.ssafy.sai.db.entity.InterestedJob;
import com.ssafy.sai.db.entity.Job;
import com.ssafy.sai.db.entity.Member;
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
