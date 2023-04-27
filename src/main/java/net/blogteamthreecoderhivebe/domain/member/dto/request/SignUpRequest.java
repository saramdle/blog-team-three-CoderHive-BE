package net.blogteamthreecoderhivebe.domain.member.dto.request;

public record SignUpRequest(
        String email,
        String nickname,
        long jobId,
        String level,
        String career
) {
}
