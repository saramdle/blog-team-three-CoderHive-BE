package net.blogteamthreecoderhivebe.domain.info.dto.response;

import net.blogteamthreecoderhivebe.domain.info.dto.SkillDto;

import java.util.List;

public record TechnologyResponse(List<SkillDto> skills) {
    public static TechnologyResponse from(List<SkillDto> skills) {
        return new TechnologyResponse(skills);
    }
}
