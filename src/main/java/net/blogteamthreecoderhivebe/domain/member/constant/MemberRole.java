package net.blogteamthreecoderhivebe.domain.member.constant;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String description;

    MemberRole(String description) {
        this.description = description;
    }
}
