package net.blogteamthreecoderhivebe.domain.info.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.entity.Location;

@Builder
public record LocationDto(Long id, String region) {
    public static LocationDto from(Location Entity) {
        return LocationDto.builder()
                .id(Entity.getId())
                .region(Entity.getRegion())
                .build();
    }
}
