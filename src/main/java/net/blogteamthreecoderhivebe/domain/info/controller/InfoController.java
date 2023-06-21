package net.blogteamthreecoderhivebe.domain.info.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.service.InfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static net.blogteamthreecoderhivebe.domain.info.dto.CareerDto.CareerList;
import static net.blogteamthreecoderhivebe.domain.info.dto.JobDto.JobList;
import static net.blogteamthreecoderhivebe.domain.info.dto.LevelDto.LevelList;
import static net.blogteamthreecoderhivebe.domain.info.dto.LocationDto.LocationList;
import static net.blogteamthreecoderhivebe.domain.info.dto.PlatformDto.PlatformList;
import static net.blogteamthreecoderhivebe.domain.info.dto.SkillDto.SkillList;

@RequiredArgsConstructor
@RequestMapping("/info")
@RestController
public class InfoController {

    private final InfoService infoService;

    @GetMapping("/locations")
    public LocationList locationList() {
        return LocationList.from(infoService.findLocations());
    }

    @GetMapping("/jobs")
    public JobList jobList() {
        return JobList.from(infoService.findJobs());
    }

    @GetMapping("/skills")
    public SkillList skillList(@RequestParam(required = false, defaultValue = "") final String keyword) {
        return SkillList.from(infoService.findTop4Skills(keyword));
    }

    @GetMapping("/careers")
    public CareerList careerList() {
        return CareerList.from(infoService.findCareers());
    }

    @GetMapping("/levels")
    public LevelList levelList() {
        return LevelList.from(infoService.findLevels());
    }

    @GetMapping("/platforms")
    public PlatformList platformList() {
        return PlatformList.from(infoService.findPlatforms());
    }
}
