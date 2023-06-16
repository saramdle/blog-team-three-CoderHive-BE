package net.blogteamthreecoderhivebe.domain.info.dto.response;

import java.util.List;

public record CareerResponse(List<String> careers) {
    public static CareerResponse from(List<String> careers) {
        return new CareerResponse(careers);
    }
}
