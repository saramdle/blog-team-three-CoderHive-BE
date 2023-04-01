package net.blogteamthreecoderhivebe.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.dto.PostDto;
import net.blogteamthreecoderhivebe.entity.constant.PostCategory;

import java.util.List;

@Builder
public record PostResponse(
        Long id,
        String location,
        String title,
        String content,
        String thumbImageUrl,
        PostCategory postCategory,
        String platforms,
        String postStatus,
        JobResponse job,
        List<String> skills,
        Boolean likeBoolean,
        int likes
) {
    public static PostResponse from(PostDto dto, List<Long> likePostIds) {
        return PostResponse.builder()
                .id(dto.id())
                .location(dto.locationDto().region())
                .title(dto.title())
                .content(dto.content())
                .thumbImageUrl(dto.thumbImageUrl())
                .postCategory(dto.postCategory())
                .platforms(dto.platforms())
                .postStatus(dto.postStatus().getDescription())
                .job(JobResponse.from(dto.jobDto()))
                .skills(dto.skills())
                .likeBoolean(
                        likePostIds.stream().anyMatch(id -> id.equals(dto.id()))
                )
                .likes(dto.likes())
                .build();
    }
}
