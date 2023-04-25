package net.blogteamthreecoderhivebe.domain.info.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.dto.SkillDto;

import java.util.List;

@Builder
public record TechnologyResponse(List<SkillDto> skills) {
    public static TechnologyResponse from(List<SkillDto> skills) {
        return TechnologyResponse.builder()
                .skills(skills)
                .build();
    }
}
