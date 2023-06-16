package net.blogteamthreecoderhivebe.domain.info.dto.response;

import net.blogteamthreecoderhivebe.domain.info.dto.JobDto;

public record JobResponse(String main, String detail) {
    public static JobResponse from(JobDto dto) {
        return new JobResponse(dto.main(), dto.detail());
    }
}
