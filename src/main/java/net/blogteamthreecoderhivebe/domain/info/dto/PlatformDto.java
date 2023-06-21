package net.blogteamthreecoderhivebe.domain.info.dto;

import java.util.List;

public class PlatformDto {

    public record PlatformList(
            List<String> platforms
    ) {
        public static PlatformList from(List<String> platforms) {
            return new PlatformList(platforms);
        }
    }
}
