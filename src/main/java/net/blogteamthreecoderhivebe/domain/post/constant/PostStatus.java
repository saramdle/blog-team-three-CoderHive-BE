package net.blogteamthreecoderhivebe.domain.post.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PostStatus {
    HIRING("모집중"),
    CLOSED("모집 완료");

    private static final String NOT_FOUND_STATUS = "[%s] 모집 상태를 찾을 수 없습니다.";

    private final String description;

    PostStatus(String description) {
        this.description = description;
    }

    public static PostStatus find(String description) {
        return Arrays.stream(values())
                .filter(status -> status.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NOT_FOUND_STATUS, description)));
    }
}
