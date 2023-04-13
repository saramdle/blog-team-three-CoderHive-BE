package net.blogteamthreecoderhivebe.dto;

import lombok.Builder;
import net.blogteamthreecoderhivebe.entity.PostJob;

@Builder
public record PostJobDto(
        Long id,
        JobDto jobDto,
        int number,
        int passNumber
) {
    public static PostJobDto from(PostJob postJob) {
        return PostJobDto.builder()
                .id(postJob.getId())
                .jobDto(JobDto.from(postJob.getJob()))
                .number(postJob.getNumber())
                .passNumber(postJob.getPassNumber())
                .build();
    }
}
