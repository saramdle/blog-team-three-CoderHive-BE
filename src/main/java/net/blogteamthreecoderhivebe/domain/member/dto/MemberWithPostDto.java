package net.blogteamthreecoderhivebe.domain.member.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.dto.response.JobResponseDto;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberCareer;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberLevel;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberRole;
import net.blogteamthreecoderhivebe.domain.post.dto.PostDto;

import java.util.List;

@Builder
public record MemberWithPostDto(
        Long id,
        JobResponseDto.All jobDto,
        String email,
        MemberLevel level,
        MemberCareer career,
        MemberRole memberRole,
        String nickname,
        String profileImageUrl,
        String introduction,
        List<String> skills,
        List<PostDto> hostPostDtos,
        List<PostDto> appliedPostDtos,
        List<PostDto> participatedPostDtos
) {
    public static MemberWithPostDto from(MemberDto memberDto, List<PostDto> hostPostDtos, List<PostDto> appliedPostDtos, List<PostDto> participatedPostDtos) {
        return MemberWithPostDto.builder()
                .id(memberDto.id())
                .jobDto(memberDto.jobDto())
                .email(memberDto.email())
                .level(memberDto.level())
                .career(memberDto.career())
                .memberRole(memberDto.memberRole())
                .nickname(memberDto.nickname())
                .profileImageUrl(memberDto.profileImageUrl())
                .introduction(memberDto.introduction())
                .skills(memberDto.skills())
                .hostPostDtos(hostPostDtos)
                .appliedPostDtos(appliedPostDtos)
                .participatedPostDtos(participatedPostDtos)
                .build();
    }
}
