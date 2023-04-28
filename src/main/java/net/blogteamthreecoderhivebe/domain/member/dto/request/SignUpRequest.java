package net.blogteamthreecoderhivebe.domain.member.dto.request;

public record SignUpRequest(
        String nickname,
        long jobId,
        String level,
        String career
) {
}
