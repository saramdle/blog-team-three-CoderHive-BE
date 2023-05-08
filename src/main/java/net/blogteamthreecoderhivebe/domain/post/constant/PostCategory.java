package net.blogteamthreecoderhivebe.domain.post.constant;

import lombok.Getter;

@Getter
public enum PostCategory {
    PROJECT("project"),
    STUDY("study");

    @Getter
    private final String description;

    private final String key;

    PostCategory(String key) {
        this.key = key;
    }
}

