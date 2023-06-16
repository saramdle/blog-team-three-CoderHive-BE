package net.blogteamthreecoderhivebe.domain.info.dto.response;

import java.util.List;
import java.util.Map;

public record JobsResponse(List<Map<String, Object>> jobs) {
    public static JobsResponse from(List<Map<String, Object>> jobs) {
        return new JobsResponse(jobs);
    }
}
