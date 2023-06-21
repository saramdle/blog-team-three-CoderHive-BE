package net.blogteamthreecoderhivebe.domain.info.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.constant.Platform;
import net.blogteamthreecoderhivebe.domain.info.dto.JobDto;
import net.blogteamthreecoderhivebe.domain.info.dto.LocationDto;
import net.blogteamthreecoderhivebe.domain.info.dto.SkillDto;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import net.blogteamthreecoderhivebe.domain.info.repository.JobRepository;
import net.blogteamthreecoderhivebe.domain.info.repository.LocationRepository;
import net.blogteamthreecoderhivebe.domain.info.repository.SkillRepository;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberCareer;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberLevel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InfoService {

    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final LocationRepository locationRepository;

    public List<LocationDto.All> findLocations() {
        return locationRepository.findAll().stream()
                .map(LocationDto.All::from)
                .toList();
    }

    public List<Map<String, Object>> findJobs() {
        List<Job> jobs = jobRepository.findAll();

        Map<String, List<JobDto.Detail>> groupingByJobMain = groupingByJobMain(jobs);
        Stream<Map.Entry<String, List<JobDto.Detail>>> sortingByJobId = sortingByJobId(groupingByJobMain);

        return sortingByJobId.map(
                entry -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("main", entry.getKey());
                    result.put("details", entry.getValue());
                    return result;
                }).toList();
    }

    private static Stream<Map.Entry<String, List<JobDto.Detail>>> sortingByJobId(Map<String, List<JobDto.Detail>> groupedMapByMain) {
        return groupedMapByMain.entrySet().stream()
                .sorted(comparing(entry ->
                        entry.getValue().stream()
                                .map(JobDto.Detail::id)
                                .min(Long::compareTo)
                                .orElse(Long.MAX_VALUE)
                ));
    }

    private static Map<String, List<JobDto.Detail>> groupingByJobMain(List<Job> jobs) {
        return jobs.stream()
                .collect(groupingBy(
                        Job::getMain,
                        mapping(JobDto.Detail::from, toList())
                ));
    }

    public List<SkillDto.All> findTop4Skills(String keyword) {
        return skillRepository.findTop4ByDetailContaining(keyword).stream()
                .map(SkillDto.All::from)
                .toList();
    }

    public List<String> findCareers() {
        return MemberCareer.toList();
    }

    public List<String> findLevels() {
        return MemberLevel.toList();
    }

    public List<String> findPlatforms() {
        return Platform.toList();
    }
}
