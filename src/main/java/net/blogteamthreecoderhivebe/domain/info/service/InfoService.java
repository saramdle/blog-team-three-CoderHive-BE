package net.blogteamthreecoderhivebe.domain.info.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.dto.LocationDto;
import net.blogteamthreecoderhivebe.domain.info.dto.SkillDto;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import net.blogteamthreecoderhivebe.domain.info.repository.JobRepository;
import net.blogteamthreecoderhivebe.domain.info.repository.LocationRepository;
import net.blogteamthreecoderhivebe.domain.info.repository.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InfoService {
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final LocationRepository locationRepository;

    public List<LocationDto> searchAllLocation() {
        return locationRepository.findAll().stream().map(LocationDto::from).toList();
    }

    public List<Map<String, Object>> searchAllJobs() {
        return jobRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Job::getMain,
                        Collectors.mapping(
                                job -> Map.of("jobId", job.getId(), "field", job.getDetail()),
                                Collectors.toList()
                        )
                ))
                .entrySet().stream()
                .sorted(Comparator.comparing(
                        entry -> entry.getValue().stream().mapToLong(m -> Math.toIntExact((Long) m.get("jobId"))).min().orElse(Long.MAX_VALUE))
                )
                .map(entry -> {
                    Map<String, Object> resultEntry = new HashMap<>();
                    resultEntry.put("main", entry.getKey());
                    resultEntry.put("details", entry.getValue());
                    return resultEntry;
                })
                .toList();
    }

    public List<SkillDto> searchTop4Skills(String keyword) {
        return skillRepository.findTop4ByDetailContaining(keyword);
    }
}
