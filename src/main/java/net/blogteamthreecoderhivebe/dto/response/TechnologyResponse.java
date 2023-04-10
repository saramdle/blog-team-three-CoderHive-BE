package net.blogteamthreecoderhivebe.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.dto.TechnologyDto;

import java.util.List;

@Builder
public record TechnologyResponse(
        List<TechnologyDto> skills
) {
    public static TechnologyResponse from(List<TechnologyDto> skills){
        return TechnologyResponse.builder()
                .skills(skills)
                .build();
    }
}
