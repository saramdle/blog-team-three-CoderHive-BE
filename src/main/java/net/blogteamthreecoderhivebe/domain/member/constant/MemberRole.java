package net.blogteamthreecoderhivebe.domain.member.constant;

import lombok.Getter;

public enum MemberRole {
    ADMIN("admin"),
    USER("user");

    @Getter
    private final String description;

    MemberRole(String description) {
        this.description = description;
    }
}
