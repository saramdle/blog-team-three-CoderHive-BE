package net.blogteamthreecoderhivebe.domain.member.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberCareer;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberLevel;
import net.blogteamthreecoderhivebe.domain.member.dto.request.SignUpRequest;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;

import static net.blogteamthreecoderhivebe.domain.member.constant.MemberRole.USER;

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
                .jobId(signUpRequest.jobId())
                .memberLevel(MemberLevel.find(signUpRequest.level()))
                .memberCareer(MemberCareer.find(signUpRequest.career()))
                .build();
    }

    public static Member toMemberWithJob(SignUpDto dto, Job job) {
        return Member.builder()
                .email(dto.email)
                .nickname(dto.nickname)
                .career(dto.memberCareer)
                .job(job)
                .level(dto.memberLevel)
                .memberRole(USER)
                .build();
    }
}
