package net.blogteamthreecoderhivebe.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.dto.LocationDto;

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
