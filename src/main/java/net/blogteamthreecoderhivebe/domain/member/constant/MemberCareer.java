package net.blogteamthreecoderhivebe.domain.member.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum MemberCareer {
    NON("미지정"),
    ASSOCIATE("1~3년차"),
    JUNIOR("3~5년차"),
    SENIOR("5~10년차"),
    PRINCIPAL("10년차 이상");

    private static final String NOT_FOUND_CAREER = "[%s] 경력을 찾을 수 없습니다.";

    private final String description;

    MemberCareer(String description) {
        this.description = description;
    }

    public static List<String> toList() {
        return Arrays.stream(MemberCareer.values())
                .map(MemberCareer::getDescription)
                .toList();
    }

    public static MemberCareer find(String description) {
        return Arrays.stream(values())
                .filter(career -> career.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NOT_FOUND_CAREER, description)));
    }
}
