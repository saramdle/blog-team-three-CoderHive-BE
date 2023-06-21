package net.blogteamthreecoderhivebe.domain.info.dto;

import java.util.List;

public class CareerDto {

    public record CareerList(
            List<String> careers
    ) {
        public static CareerList from(List<String> careers) {
            return new CareerList(careers);
        }
    }
}
