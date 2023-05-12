package net.blogteamthreecoderhivebe.domain.post.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PostCategory {
    PROJECT("project"),
    STUDY("study");

    private static final String NOT_FOUND_CATEGORY = "[%s] 카테고리를 찾을 수 없습니다.";

    private final String key;

    PostCategory(String key) {
        this.key = key;
    }

    public static PostCategory find(String key) {
        return Arrays.stream(values())
                .filter(category -> category.key.equals(key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NOT_FOUND_CATEGORY, key)));
    }
}

