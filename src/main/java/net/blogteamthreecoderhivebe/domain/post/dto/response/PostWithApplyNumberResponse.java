package net.blogteamthreecoderhivebe.domain.post.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;
import net.blogteamthreecoderhivebe.domain.post.dto.PostWithApplyNumberDto;

import java.util.List;

@Builder
public record PostWithApplyNumberResponse(
        Long id,
        PostCategory postCategory,
        PostStatus postStatus,
        String title,
        String location,
        List<String> skills,
        int likes,
        boolean memberLike,
        String thumbImageUrl,
        int postJobTotal,
        int userApplyPass
) {
    public static PostWithApplyNumberResponse from(PostWithApplyNumberDto dto, List<Long> likePostIds) {
        return PostWithApplyNumberResponse.builder()
                .id(dto.id())
                .postCategory(dto.postCategory())
                .postStatus(dto.postStatus())
                .title(dto.title())
                .location(dto.locationDto().region())
                .skills(dto.skills())
                .likes(dto.likes())
                .memberLike(
                        likePostIds.stream().anyMatch(id -> id.equals(dto.id()))
                )
                .thumbImageUrl(dto.thumbImageUrl())
                .postJobTotal(dto.number())
                .userApplyPass(dto.passNumber())
                .build();
    }
}
