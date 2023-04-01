package net.blogteamthreecoderhivebe.dto.response;

import lombok.Builder;

@Builder
public record TechnologyDto(
        Long id,
        String detail
) {
}
