package net.blogteamthreecoderhivebe.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.entity.Post;
import net.blogteamthreecoderhivebe.entity.PostJob;
import net.blogteamthreecoderhivebe.entity.constant.PostCategory;
import net.blogteamthreecoderhivebe.entity.constant.PostStatus;

import java.util.List;

@Builder
public record PostWithPostJobsDto(
        Long id,
        LocationDto locationDto,
        String title,
        String content,
        String thumbImageUrl,
        PostCategory postCategory,
        String platforms,
        PostStatus postStatus,
        List<PostJobDto> postJobDtos,
        JobDto jobDto,
        List<String> skills,
        int likes
) {
    public static PostWithPostJobsDto from(Post post, List<PostJob> postJobs, List<String> skills) {
        return PostWithPostJobsDto.builder()
                .id(post.getId())
                .locationDto(LocationDto.from(post.getLocation()))
                .title(post.getTitle())
                .content(post.getContent())
                .thumbImageUrl(post.getThumbImageUrl())
                .postCategory(post.getPostCategory())
                .platforms(post.getPlatforms())
                .postStatus(post.getPostStatus())
                .postJobDtos(
                        postJobs.stream().map(PostJobDto::from).toList()
                )
                .jobDto(JobDto.from(post.getJob()))
                .skills(skills)
                .likes(post.getLikingMembers().size())
                .build();
    }
}
