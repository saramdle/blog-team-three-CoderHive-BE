package net.blogteamthreecoderhivebe.domain.post.dto.request;

import java.util.List;

public class PostRequestDto {
    public record Save(
            String category,
            String title,
            Long locationId,
            List<RecruitmentJobRequestDto.Save> recruitmentJobs,
            Long myJobId,
            List<Long> skillIds,
            String thumbImageUrl,
            String content,
            String platforms
    ) {}
}
