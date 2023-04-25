package net.blogteamthreecoderhivebe.domain.post.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.dto.LocationDto;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;

import java.util.List;


@Builder
public record PostWithApplyNumberDto(
        Long id,
        PostCategory postCategory,
        PostStatus postStatus,
        String title,
        LocationDto locationDto,
        List<String> skills,
        int likes,
        String thumbImageUrl,
        int number,
        int passNumber
) {
    public static PostWithApplyNumberDto from(Post post, List<String> skills, int number, int passNumber) {
        return PostWithApplyNumberDto.builder()
                .id(post.getId())
                .postCategory(post.getPostCategory())
                .postStatus(post.getPostStatus())
                .title(post.getTitle())
                .locationDto(LocationDto.from(post.getLocation()))
                .thumbImageUrl(post.getThumbImageUrl())
                .skills(skills)
                .likes(post.getLikingMembers().size())
                .thumbImageUrl(post.getThumbImageUrl())
                .number(number)
                .passNumber(passNumber)
                .build();
    }
}
