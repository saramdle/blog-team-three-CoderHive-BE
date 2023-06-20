package net.blogteamthreecoderhivebe.domain.member.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.dto.response.JobResponseDto;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberCareer;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberLevel;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberRole;
import net.blogteamthreecoderhivebe.domain.member.dto.MemberWithPostDto;
import net.blogteamthreecoderhivebe.domain.post.dto.response.PostResponse;

import java.util.List;

@Builder
public record MemberInfoWithPostResponse(
        Long id,
        String profileImageUrl,
        String nickname,
        JobResponseDto.Info job,
        MemberLevel level,
        MemberCareer career,
        MemberRole memberRole,
        List<String> skills,
        String introduction,
        List<PostResponse> hostingPost,
        List<PostResponse> participatedPost
) {
    public static MemberInfoWithPostResponse from(MemberWithPostDto dto, List<Long> likePostIds) {
        return MemberInfoWithPostResponse.builder()
                .id(dto.id())
                .job(JobResponseDto.Info.from(dto.jobDto()))
                .level(dto.level())
                .career(dto.career())
                .memberRole(dto.memberRole())
                .nickname(dto.nickname())
                .profileImageUrl(dto.profileImageUrl())
                .introduction(dto.introduction())
                .skills(dto.skills())
                .hostingPost(
                        dto.hostPostDtos().stream()
                                .map(d -> PostResponse.from(d, likePostIds))
                                .toList()
                )
                .participatedPost(
                        dto.participatedPostDtos().stream()
                                .map(d -> PostResponse.from(d, likePostIds))
                                .toList()
                )
                .build();
    }
}
