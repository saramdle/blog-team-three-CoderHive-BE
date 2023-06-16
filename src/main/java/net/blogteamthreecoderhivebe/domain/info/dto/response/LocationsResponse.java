package net.blogteamthreecoderhivebe.domain.info.dto.response;

import net.blogteamthreecoderhivebe.domain.info.dto.LocationDto;

import java.util.List;

public record LocationsResponse(List<LocationDto> locations) {
    public static LocationsResponse from(List<LocationDto> locations) {
        return new LocationsResponse(locations);
    }
}
