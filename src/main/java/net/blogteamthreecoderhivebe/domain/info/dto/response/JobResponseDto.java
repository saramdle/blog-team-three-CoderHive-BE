package net.blogteamthreecoderhivebe.domain.info.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;

import java.util.List;
import java.util.Map;

public class JobResponseDto {

    @Builder
    public record Detail(
            Long id,
            String detail
    ) {
        public static Detail from(Job entity) {
            return Detail.builder()
                    .id(entity.getId())
                    .detail(entity.getDetail())
                    .build();
        }
    }

    @Builder
    public record JobList(
            List<Map<String, Object>> jobs
    ) {
        public static JobList from(List<Map<String, Object>> jobs) {
            return JobList.builder()
                    .jobs(jobs)
                    .build();
        }
    }
}
