package net.blogteamthreecoderhivebe.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.dto.response.*;
import net.blogteamthreecoderhivebe.entity.constant.MemberCareer;
import net.blogteamthreecoderhivebe.entity.constant.MemberLevel;
import net.blogteamthreecoderhivebe.entity.constant.Platform;
import net.blogteamthreecoderhivebe.service.TableInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("info")
@RestController
public class TableInfoController {
    private final TableInfoService tableInfoService;

    @GetMapping("/locations")
    public LocationsResponse searchAllLocation() {
        return LocationsResponse.from(tableInfoService.searchAllLocation());
    }
    @GetMapping("/jobs")
    public JobsResponse searchAllJobs() {
        return JobsResponse.from(tableInfoService.searchAllJobs());
    }

    @GetMapping("/skills")
    public TechnologyResponse searchTop4Skills(
            @RequestParam(required = false, defaultValue="") final String keyword) {
        return TechnologyResponse.from(tableInfoService.searchTop4Skills(keyword));
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
