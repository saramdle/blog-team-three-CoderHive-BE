package net.blogteamthreecoderhivebe.domain.post.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.service.JobService;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;
import net.blogteamthreecoderhivebe.domain.post.repository.RecruitmentJobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static net.blogteamthreecoderhivebe.domain.post.dto.request.RecruitmentJobRequestDto.SaveRequest;

@RequiredArgsConstructor
@Transactional
@Service
public class RecruitmentJobService {
    private final JobService jobService;
    private final RecruitmentJobRepository recruitmentJobRepository;

    public void save(List<SaveRequest> saveRequests, Post post) {
        for (SaveRequest saveRequest : saveRequests) {
            RecruitmentJob recruitmentJob = RecruitmentJob.builder()
                    .post(post)
                    .job(jobService.findOne(saveRequest.jobId()))
                    .number(saveRequest.number())
                    .build();
            recruitmentJobRepository.save(recruitmentJob);
        }
    }
}
