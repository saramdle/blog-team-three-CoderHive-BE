package net.blogteamthreecoderhivebe.domain.post.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.dto.JobDto;
import net.blogteamthreecoderhivebe.domain.info.dto.LocationDto;
import net.blogteamthreecoderhivebe.domain.member.dto.MemberDto;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;

import java.util.List;

@Builder
public record PostDto(
        Long id,
        MemberDto memberDto,
        LocationDto locationDto,
        String title,
        String content,
        String thumbImageUrl,
        PostCategory postCategory,
        String platforms,
        PostStatus postStatus,
        JobDto jobDto,
        List<String> skills,
        int likes
) {
    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .memberDto(MemberDto.from(post.getMember()))
                .locationDto(LocationDto.from(post.getLocation()))
                .title(post.getTitle())
                .content(post.getContent())
                .thumbImageUrl(post.getThumbImageUrl())
                .postCategory(post.getPostCategory())
                .platforms(post.getPlatforms())
                .postStatus(post.getPostStatus())
                .jobDto(JobDto.from(post.getJob()))
                .likes(post.getHearts().size())
                .build();
    }

    public static PostDto from(Post post, List<String> skills) {
        return PostDto.builder()
                .id(post.getId())
                .memberDto(MemberDto.from(post.getMember()))
                .locationDto(LocationDto.from(post.getLocation()))
                .title(post.getTitle())
                .content(post.getContent())
                .thumbImageUrl(post.getThumbImageUrl())
                .postCategory(post.getPostCategory())
                .platforms(post.getPlatforms())
                .postStatus(post.getPostStatus())
                .jobDto(JobDto.from(post.getJob()))
                .skills(skills)
                .likes(post.getHearts().size())
                .build();
    }
}
