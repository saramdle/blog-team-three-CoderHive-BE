package net.blogteamthreecoderhivebe.domain.info.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import net.blogteamthreecoderhivebe.domain.info.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class JobService {
    private static final String NOT_FOUND_JOB = "ID[%s] 직무를 찾을 수 없습니다.";

    private final JobRepository jobRepository;

    public Job findOne(Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_JOB, jobId)));
    }
}
