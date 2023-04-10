package net.blogteamthreecoderhivebe.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CareerResponse(
        List<String> careers
) {
    public static CareerResponse from(List<String> careers) {
        return CareerResponse.builder()
                .careers(careers)
                .build();
    }
}
