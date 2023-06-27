package net.blogteamthreecoderhivebe.domain.member.constant;

import lombok.Getter;

@Getter
public enum MemberRole {
    GUEST("ROLE_GUEST"),
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String description;

    MemberRole(String description) {
        this.description = description;
    }

    public boolean isGuest() {
        return this == GUEST;
    }
}
