package net.blogteamthreecoderhivebe.entity.constant;

import lombok.Getter;

public enum PostCategory {
    PROJECT("project"), STUDY("study");
    @Getter
    private final String description;

    PostCategory(String description) {
        this.description = description;
    }
}

