package net.blogteamthreecoderhivebe.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.entity.Technology;

@Builder
public record TechnologyDto(
        Long id,
        String detail
) {
    public static TechnologyDto from(Technology entity) {
        if (entity == null) return null;
        return TechnologyDto.builder()
                .id(entity.getId())
                .detail(entity.getDetail())
                .build();
    }

    public Technology toEntity() {
        return Technology.builder()
                .id(id)
                .detail(detail)
                .build();
    }
}
