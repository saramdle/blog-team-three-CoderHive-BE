package net.blogteamthreecoderhivebe.domain.info.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record PlatformResponse(
        List<String> platforms
) {
    public static PlatformResponse from(List<String> platforms) {
        return PlatformResponse.builder()
                .platforms(platforms)
                .build();
    }
}
