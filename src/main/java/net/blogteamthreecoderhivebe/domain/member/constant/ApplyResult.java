package net.blogteamthreecoderhivebe.domain.member.constant;

import lombok.Getter;

@Getter
public enum ApplyResult {
    NONE("미신청"),
    APPLY("신청"),
    PASS("합격"),
    FAIL("불합격");

    private final String description;

    ApplyResult(String description) {
        this.description = description;
    }
}
