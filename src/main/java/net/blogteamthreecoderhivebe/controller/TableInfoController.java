package net.blogteamthreecoderhivebe.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.dto.response.JobsResponse;
import net.blogteamthreecoderhivebe.dto.response.LocationsResponse;
import net.blogteamthreecoderhivebe.service.TableInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
