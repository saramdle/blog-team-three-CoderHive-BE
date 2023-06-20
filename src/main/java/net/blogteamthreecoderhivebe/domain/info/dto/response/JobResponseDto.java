package net.blogteamthreecoderhivebe.domain.info.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;

import java.util.List;
import java.util.Map;

public class JobResponseDto {

    @Builder
    public record All(
            Long id,
            String main,
            String detail
    ) {
        public static All from(Job entity) {
            if (entity == null) return null;
            return All.builder()
                    .id(entity.getId())
                    .main(entity.getMain())
                    .detail(entity.getDetail())
                    .build();
        }
    }

    @Builder
    public record Info(
            String main,
            String detail
    ) {
        public static Info from(All dto) {
            return Info.builder()
                    .main(dto.main())
                    .detail(dto.detail())
                    .build();
        }
    }

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
