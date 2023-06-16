package net.blogteamthreecoderhivebe.domain.post.dto.request;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;

import java.util.List;

public class PostRequestDto {
    @Builder
    public record Save(
            String category,
            String title,
            Long locationId,
            List<RecruitJobRequestDto.Save> recruitmentJobs,
            Long myJobId,
            List<Long> skillIds,
            String thumbImageUrl,
            String content,
            String platforms
    ) {}

    public record SearchCond(
            PostCategory postCategory,
            List<Long> locations,
            List<Long> jobs,
            PostStatus postStatus
    ) {}
}
