package net.blogteamthreecoderhivebe.domain.post.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.service.vo.RecruitJobResult;

import java.util.List;

@Builder
public record PostSearchResponse(
        Long id,
        PostCategory postCategory,
        PostStatus postStatus,
        String title,
        String location,
        List<String> skills,
        int likes,
        boolean memberLike,
        String thumbImageUrl,
        int totalRecruitment,
        int totalApplyPass
) {
    public static PostSearchResponse from(Post post, boolean isMemberHeartPost, RecruitJobResult recruitJobResult) {
        return PostSearchResponse.builder()
                .id(post.getId())
                .postCategory(post.getPostCategory())
                .postStatus(post.getPostStatus())
                .title(post.getTitle())
                .location(post.getLocation().getRegion())
                .skills(post.getSkillDetails())
                .likes(post.getTotalHearts())
                .memberLike(isMemberHeartPost)
                .thumbImageUrl(post.getThumbImageUrl())
                .totalRecruitment(recruitJobResult.totalNumber())
                .totalApplyPass(recruitJobResult.passNumber())
                .build();
    }
}
