package net.blogteamthreecoderhivebe.domain.info.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.constant.Platform;
import net.blogteamthreecoderhivebe.domain.info.dto.response.*;
import net.blogteamthreecoderhivebe.domain.info.service.InfoService;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberCareer;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberLevel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static net.blogteamthreecoderhivebe.domain.info.dto.response.JobResponseDto.JobList;

@RequiredArgsConstructor
@RequestMapping("/info")
@RestController
public class InfoController {

    private final InfoService infoService;

    @GetMapping("/locations")
    public LocationsResponse locationList() {
        return LocationsResponse.from(infoService.findLocations());
    }

    @GetMapping("/jobs")
    public JobList jobList() {
        return JobList.from(infoService.findJobs());
    }

    @GetMapping("/skills")
    public SkillResponse skillList(@RequestParam(required = false, defaultValue = "") final String keyword) {
        return SkillResponse.from(infoService.findTop4Skills(keyword));
    }

    @GetMapping("/careers")
    public CareerResponse careerList() {
        return CareerResponse.from(MemberCareer.toList());
    }

    @GetMapping("/levels")
    public LevelResponse levelList() {
        return LevelResponse.from(MemberLevel.toList());
    }

    @GetMapping("/platforms")
    public PlatformResponse platformList() {
        return PlatformResponse.from(Platform.toList());
    }
}
