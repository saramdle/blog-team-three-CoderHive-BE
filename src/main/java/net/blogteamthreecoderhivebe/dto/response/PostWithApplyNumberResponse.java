package net.blogteamthreecoderhivebe.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.dto.PostWithApplyNumberDto;
import net.blogteamthreecoderhivebe.entity.constant.PostCategory;
import net.blogteamthreecoderhivebe.entity.constant.PostStatus;

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
