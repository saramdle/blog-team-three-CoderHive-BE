package net.blogteamthreecoderhivebe.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record LevelResponse(
        List<String> levels
) {
    public static LevelResponse from(List<String> levels) {
        return LevelResponse.builder()
                .levels(levels)
                .build();
    }
}
