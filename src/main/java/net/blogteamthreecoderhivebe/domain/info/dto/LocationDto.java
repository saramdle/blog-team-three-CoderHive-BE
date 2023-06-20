package net.blogteamthreecoderhivebe.domain.info.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.entity.Location;

import java.util.List;

public class LocationDto {

    @Builder
    public record All(
            Long id,
            String region
    ) {
        public static All from(Location entity) {
            return All.builder()
                    .id(entity.getId())
                    .region(entity.getRegion())
                    .build();
        }
    }

    @Builder
    public record LocationList(
            List<All> locations
    ) {
        public static LocationList from(List<All> dtos) {
            return LocationList.builder()
                    .locations(dtos)
                    .build();
        }
    }
}
