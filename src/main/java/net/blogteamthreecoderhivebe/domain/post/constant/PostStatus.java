package net.blogteamthreecoderhivebe.domain.post.constant;

import lombok.Getter;

public enum PostStatus {
    HIRING("모집중"), CLOSED("모집 완료");
    @Getter
    private final String description;

    PostStatus(String description) {
        this.description = description;
    }
}
