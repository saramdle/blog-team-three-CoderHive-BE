package net.blogteamthreecoderhivebe.domain.member.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.dto.JobDto;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberCareer;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberLevel;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberRole;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;

import java.util.List;

@Builder
public record MemberDto(
        Long id,
        JobDto jobDto,
        String email,
        MemberLevel level,
        MemberCareer career,
        MemberRole memberRole,
        String nickname,
        String profileImageUrl,
        String introduction,
        List<String> skills
) {
    public static MemberDto from(Member entity) {
        return MemberDto.builder()
                .id(entity.getId())
                .jobDto(JobDto.from(entity.getJob()))
                .email(entity.getEmail())
                .level(entity.getLevel())
                .career(entity.getCareer())
                .memberRole(entity.getMemberRole())
                .nickname(entity.getNickname())
                .profileImageUrl(entity.getProfileImageUrl())
                .introduction(entity.getIntroduction())
                .build();
    }

    public static MemberDto from(Member entity, List<String> skills) {
        return MemberDto.builder()
                .id(entity.getId())
                .jobDto(JobDto.from(entity.getJob()))
                .email(entity.getEmail())
                .level(entity.getLevel())
                .career(entity.getCareer())
                .memberRole(entity.getMemberRole())
                .nickname(entity.getNickname())
                .profileImageUrl(entity.getProfileImageUrl())
                .introduction(entity.getIntroduction())
                .skills(skills)
                .build();
    }
}
