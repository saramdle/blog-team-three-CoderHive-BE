package net.blogteamthreecoderhivebe.domain.member.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberCareer;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberLevel;
import net.blogteamthreecoderhivebe.domain.member.dto.request.SignUpRequest;

@Builder
public record SignUpDto(
        String email,
        String nickname,
        long jobId,
        MemberLevel memberLevel,
        MemberCareer memberCareer
) {
    public static SignUpDto of(SignUpRequest signUpRequest, String email) {
        return SignUpDto.builder()
                .email(email)
                .nickname(signUpRequest.nickname())
                .jobId(Long.parseLong(signUpRequest.jobId()))
                .memberLevel(MemberLevel.find(signUpRequest.level()))
                .memberCareer(MemberCareer.find(signUpRequest.career()))
                .build();
    }
}
