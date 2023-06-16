package net.blogteamthreecoderhivebe.domain.info.dto.response;

import java.util.List;

public record LevelResponse(List<String> levels) {
    public static LevelResponse from(List<String> levels) {
        return new LevelResponse(levels);
    }
}
