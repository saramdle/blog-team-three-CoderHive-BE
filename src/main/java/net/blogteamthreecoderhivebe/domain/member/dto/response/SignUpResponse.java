package net.blogteamthreecoderhivebe.domain.member.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;

import static net.blogteamthreecoderhivebe.domain.member.constant.MemberRole.USER;

@Builder
public record SignUpResponse(
        boolean isSignUp,
        String email,
        String nickname,
        String profileThumbUrl
) {
    public static SignUpResponse from(Member member) {
        return SignUpResponse.builder()
                .isSignUp(member.getMemberRole() == USER)
                .email(member.getEmail())
                .nickname(member.getNickname())
                .profileThumbUrl(member.getProfileImageUrl())
                .build();
    }
}
