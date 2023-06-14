package net.blogteamthreecoderhivebe.domain.post.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;

import java.util.List;

import static net.blogteamthreecoderhivebe.domain.member.dto.response.MemberResponseDto.MemberInfoOnPostDetail;
import static net.blogteamthreecoderhivebe.domain.post.dto.response.RecruitJobResponseDto.RecruitInfoOnPostDetail;

public class PostResponseDto {

    /**
     * 게시글 등록
     */
    public record Save(
            Long postId
    ) {
    }

    /**
     * 게시글 상세 조회
     */
    @Builder
    public record Detail(
            Long postId,
            String category,
            String title,
            String location,
            String status,
            List<RecruitInfoOnPostDetail> recruitInfos,
            List<String> skills,
            List<String> platforms,
            MemberInfoOnPostDetail leader,
            List<MemberInfoOnPostDetail> members,
            String content,
            int likes
    ) {
        public static Detail from(Post post, List<RecruitInfoOnPostDetail> recruitInfos,
                                  MemberInfoOnPostDetail leader, List<MemberInfoOnPostDetail> members) {
            return Detail.builder()
                    .postId(post.getId())
                    .category(post.getPostCategory().toString())
                    .title(post.getTitle())
                    .location(post.getLocation().getRegion())
                    .status(post.getPostStatus().getDescription())
                    .recruitInfos(recruitInfos)
                    .skills(post.getSkillDetails())
                    .platforms(post.getPlatformList())
                    .leader(leader)
                    .members(members)
                    .content(post.getContent())
                    .likes(post.getTotalHearts())
                    .build();
        }
    }
}
