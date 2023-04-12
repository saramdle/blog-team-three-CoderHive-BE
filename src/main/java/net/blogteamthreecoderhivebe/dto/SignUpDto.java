package net.blogteamthreecoderhivebe.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.dto.request.SignUpRequest;
import net.blogteamthreecoderhivebe.entity.constant.MemberCareer;
import net.blogteamthreecoderhivebe.entity.constant.MemberLevel;

@Builder
public record SignUpDto(
        String email,
        String nickname,
        long jobId,
        MemberLevel level,
        MemberCareer career
) {
    public static SignUpDto from(SignUpRequest signUpRequest) {
        return SignUpDto.builder()
                .email(signUpRequest.email())
                .nickname(signUpRequest.nickname())
                .jobId(signUpRequest.jobId())
                .level(MemberLevel.of(signUpRequest.level()))
                //.level(signUpRequest.level())
                .career(MemberCareer.of(signUpRequest.career()))
                .build();
    }


}
