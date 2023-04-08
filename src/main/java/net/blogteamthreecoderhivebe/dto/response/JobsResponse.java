package net.blogteamthreecoderhivebe.dto.response;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record JobsResponse (
        List<Map<String, Object>> jobs
) {
    public static JobsResponse from(List<Map<String, Object>> jobs){
        return JobsResponse.builder()
                .jobs(jobs)
                .build();
    }
}
