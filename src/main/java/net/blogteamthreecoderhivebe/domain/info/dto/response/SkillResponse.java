package net.blogteamthreecoderhivebe.domain.info.dto.response;

import net.blogteamthreecoderhivebe.domain.info.dto.SkillDto;

import java.util.List;

public record SkillResponse(List<SkillDto> skills) {
    public static SkillResponse from(List<SkillDto> skills) {
        return new SkillResponse(skills);
    }
}
