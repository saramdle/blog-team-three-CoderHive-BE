package net.blogteamthreecoderhivebe.domain.member.constant;

import lombok.Getter;

@Getter
public enum ApplicationResult {
    NONE("미신청"),
    APPLY("신청"),
    PASS("합격"),
    FAIL("불합격");

    private final String description;

    ApplicationResult(String description) {
        this.description = description;
    }
}
