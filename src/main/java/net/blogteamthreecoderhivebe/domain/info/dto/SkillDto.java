package net.blogteamthreecoderhivebe.domain.info.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.entity.Skill;

import java.util.List;

public class SkillDto {

    @Builder
    public record All(
            Long id,
            String detail
    ) {
        public static All from(Skill entity) {
            return All.builder()
                    .id(entity.getId())
                    .detail(entity.getDetail())
                    .build();
        }
    }

    @Builder
    public record SkillList(
            List<All> skills
    ) {
        public static SkillList from(List<All> dtos) {
            return SkillList.builder()
                    .skills(dtos)
                    .build();
        }
    }
}
