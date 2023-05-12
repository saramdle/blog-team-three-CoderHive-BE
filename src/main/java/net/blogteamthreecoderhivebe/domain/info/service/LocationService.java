package net.blogteamthreecoderhivebe.domain.info.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.entity.Location;
import net.blogteamthreecoderhivebe.domain.info.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LocationService {
    private static final String NOT_FOUND_LOCATION = "ID[%s] 지역을 찾을 수 없습니다.";

    private final LocationRepository locationRepository;

    public Location findOne(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_LOCATION, locationId)));
    }
}

