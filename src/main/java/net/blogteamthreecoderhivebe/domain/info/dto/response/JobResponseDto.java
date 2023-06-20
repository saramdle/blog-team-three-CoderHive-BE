package net.blogteamthreecoderhivebe.domain.info.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;

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
}
