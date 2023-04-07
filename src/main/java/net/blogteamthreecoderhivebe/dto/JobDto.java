package net.blogteamthreecoderhivebe.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.entity.Job;

@Builder
public record JobDto(
        Long id,
        String main,
        String detail
) {
     public static JobDto from(Job entity) {
         if (entity == null) return null;
         return JobDto.builder()
                .id(entity.getId())
                .main(entity.getMain())
                .detail(entity.getDetail())
                .build();
    }

    public Job toEntity() {
        return Job.builder()
                .id(id)
                .main(main)
                .detail(detail)
                .build();
    }
}
