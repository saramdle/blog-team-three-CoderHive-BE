package net.blogteamthreecoderhivebe.domain.member.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MemberLevel {
    NEWBIE("초심자"),
    BEGINNER("초보"),
    INTERMEDIATE("중수"),
    EXPERT("고수"),
    MASTER("구루");

    private static final String NOT_FOUND_LEVEL = "[%s] 레벨을 찾을 수 없습니다.";

    @Getter
    private final String description;

    MemberLevel(String description) {
        this.description = description;
    }

    public static List<String> toList() {
        return Arrays.stream(MemberLevel.values())
                .map(MemberLevel::getDescription)
                .collect(Collectors.toList());
    }

    public static MemberLevel find(String description) {
        return Arrays.stream(values())
                .filter(level -> level.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NOT_FOUND_LEVEL, description)));
    }

//    public static MemberLevel of(String source) {
//        if (source == null) {
//            throw new IllegalArgumentException();
//        }
//
//        for (MemberLevel ml : MemberLevel.values()) {
//            if (ml.description.equals(source)) {
//                return ml;
//            }
//        }
//
//        throw new IllegalArgumentException("일치하는 경력 목록이 없습니다.");
//    }
}
