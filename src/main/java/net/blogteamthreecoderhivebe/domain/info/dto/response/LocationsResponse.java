package net.blogteamthreecoderhivebe.domain.info.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.dto.LocationDto;

import java.util.List;

@Builder
public record LocationsResponse(
        List<LocationDto> locations
) {
    public static LocationsResponse from(List<LocationDto> locations) {
        return LocationsResponse.builder()
                .locations(locations)
                .build();
    }
}
