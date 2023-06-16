package net.blogteamthreecoderhivebe.domain.info.dto.response;

import java.util.List;

public record PlatformResponse(List<String> platforms) {
    public static PlatformResponse from(List<String> platforms) {
        return new PlatformResponse(platforms);
    }
}
