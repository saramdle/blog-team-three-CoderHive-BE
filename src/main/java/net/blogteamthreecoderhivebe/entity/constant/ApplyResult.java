package net.blogteamthreecoderhivebe.entity.constant;

import lombok.Getter;

public enum ApplyResult {
    FAIL("불합격"), NON("지원"), PASS("합격");

    @Getter
    private final String description;

    ApplyResult(String description) {
        this.description = description;
    }
}
