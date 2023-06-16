package net.blogteamthreecoderhivebe.domain.info.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.entity.Skill;

@Builder
public record SkillDto(Long id, String detail) {
    public static SkillDto from(Skill entity) {
        if (entity == null) return null;
        return SkillDto.builder()
                .id(entity.getId())
                .detail(entity.getDetail())
                .build();
    }
}
