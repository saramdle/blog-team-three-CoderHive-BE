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
public record MyInfoWithPostResponse(
        Long id,
        String profileImageUrl,
        String nickname,
        String email,
        JobResponseDto.Info job,
        MemberLevel level,
        MemberCareer career,
        MemberRole memberRole,
        List<String> skills,
        String introduction,
        List<PostResponse> hostingPost,
        List<PostResponse> appliedPost,
        List<PostResponse> participatedPost
) {
    public static MyInfoWithPostResponse from(MemberWithPostDto dto, List<Long> lidePostIds) {
        return MyInfoWithPostResponse.builder()
                .id(dto.id())
                .job(JobResponseDto.Info.from(dto.jobDto()))
                .email(dto.email())
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
                .appliedPost(
                        dto.appliedPostDtos().stream()
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
