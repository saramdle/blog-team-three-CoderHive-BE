package net.blogteamthreecoderhivebe.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.dto.MemberWithPostDto;
import net.blogteamthreecoderhivebe.entity.constant.MemberCareer;
import net.blogteamthreecoderhivebe.entity.constant.MemberLevel;
import net.blogteamthreecoderhivebe.entity.constant.MemberRole;
import net.blogteamthreecoderhivebe.entity.constant.PostStatus;

import java.util.List;

@Builder
public record MemberInfoWithPostResponse(
        Long id,
        String profileImageUrl,
        String nickname,
        JobResponse job,
        MemberLevel level,
        MemberCareer career,
        MemberRole memberRole,
        List<String> skills,
        String introduction,
        List<PostResponse> hostingPost,
        List<PostResponse> participatedPost
) {
    public static MemberInfoWithPostResponse from(MemberWithPostDto dto, List<Long> lidePostIds) {
        return MemberInfoWithPostResponse.builder()
                .id(dto.id())
                .job(JobResponse.from(dto.jobDto()))
                .level(dto.level())
                .career(dto.career())
                .memberRole(dto.memberRole())
                .nickname(dto.nickname())
                .profileImageUrl(dto.profileImageUrl())
                .introduction(dto.introduction())
                .skills(dto.skills())
                .hostingPost(
                        dto.hostPostDtos().stream()
                                .map(d -> PostResponse.from(d, lidePostIds))
                                .toList()
                )
                .participatedPost(
                        dto.participatedPostDtos().stream()
                                .map(d -> PostResponse.from(d, lidePostIds))
                                .toList()
                )
                .build();
    }
}
