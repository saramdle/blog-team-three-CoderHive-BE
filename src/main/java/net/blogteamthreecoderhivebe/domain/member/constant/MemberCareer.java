package net.blogteamthreecoderhivebe.domain.member.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MemberCareer {
    NON("미지정"), ASSOCIATE("1~3년차"), JUNIOR("3~5년차"), SENIOR("5~10년차"), PRINCIPAL("10년차 이상");

    @Getter
    private final String description;

    MemberCareer(String description) {
        this.description = description;
    }

    public static List<String> toList() {
        return Arrays.stream(MemberCareer.values())
                .map(MemberCareer::getDescription)
                .collect(Collectors.toList());
    }

    public static MemberCareer of(String source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }

        for (MemberCareer mc : MemberCareer.values()) {
            if (mc.description.equals(source)) {
                return mc;
            }
        }

        throw new IllegalArgumentException("일치하는 경력 목록이 없습니다.");
    }
}
