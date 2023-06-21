package net.blogteamthreecoderhivebe.domain.info.dto;

import java.util.List;

public class LevelDto {

    public record LevelList(
            List<String> levels
    ) {
        public static LevelList from(List<String> levels) {
            return new LevelList(levels);
        }
    }
}
