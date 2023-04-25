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

@RequiredArgsConstructor
@RequestMapping("info")
@RestController
public class InfoController {
    private final InfoService infoService;

    @GetMapping("/locations")
    public LocationsResponse searchAllLocation() {
        return LocationsResponse.from(infoService.searchAllLocation());
    }

    @GetMapping("/jobs")
    public JobsResponse searchAllJobs() {
        return JobsResponse.from(infoService.searchAllJobs());
    }

    @GetMapping("/skills")
    public TechnologyResponse searchTop4Skills(@RequestParam(required = false, defaultValue = "") final String keyword) {
        return TechnologyResponse.from(infoService.searchTop4Skills(keyword));
    }

    @GetMapping("/careers")
    public CareerResponse searchAllCareer() {
        return CareerResponse.from(MemberCareer.toList());
    }

    @GetMapping("/levels")
    public LevelResponse searchAllLevel() {
        return LevelResponse.from(MemberLevel.toList());
    }

    @GetMapping("/platforms")
    public PlatformResponse searchAllPlatform() {
        return PlatformResponse.from(Platform.toList());
    }
}
