package com.ssafy.sai.domain.job.service;

import com.ssafy.sai.domain.job.domain.InterestedJob;
import com.ssafy.sai.domain.job.repository.InterestedJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InterestedJobService {
    private final InterestedJobRepository interestedJobRepository;
}
