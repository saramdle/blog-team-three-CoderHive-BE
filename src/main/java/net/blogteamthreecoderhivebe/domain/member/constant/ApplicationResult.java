package net.blogteamthreecoderhivebe.domain.member.constant;

import lombok.Getter;

public enum ApplicationResult {
    FAIL("불합격"), NON("지원"), PASS("합격");

    @Getter
    private final String description;

    ApplicationResult(String description) {
        this.description = description;
    }
}
