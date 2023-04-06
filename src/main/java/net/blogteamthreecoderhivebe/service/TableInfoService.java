package net.blogteamthreecoderhivebe.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.dto.LocationDto;
import net.blogteamthreecoderhivebe.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TableInfoService {
    private final LocationRepository locationRepository;

    public List<LocationDto> searchAllLocation() {
        return locationRepository.findAll().stream().map(LocationDto::from).toList();
    }
}
