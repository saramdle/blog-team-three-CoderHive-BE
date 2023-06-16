package net.blogteamthreecoderhivebe.domain.post.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.service.JobService;
import net.blogteamthreecoderhivebe.domain.post.dto.request.RecruitJobRequestDto;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitJob;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class RecruitJobService {
    private final JobService jobService;

    public void save(List<RecruitJobRequestDto.Save> dtos, Post post) {
        dtos.stream()
                .map(this::make)
                .forEach(post::addRecruitJob);
    }

    private RecruitJob make(RecruitJobRequestDto.Save dto) {
        return RecruitJob.builder()
                .job(jobService.findOne(dto.jobId()))
                .number(dto.number())
                .build();
    }
}
