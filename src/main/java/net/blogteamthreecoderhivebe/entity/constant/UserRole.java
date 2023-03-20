package net.blogteamthreecoderhivebe.entity.constant;

import lombok.Getter;

public enum UserRole {
    ADMIN("admin"), user("user");
    @Getter
    private final String description;

    UserRole(String description) {
        this.description = description;
    }
}
