package net.blogteamthreecoderhivebe.domain.info.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Platform {
    NON("미정(논의 후 확정)"), WEB("반응형 웹(PC/모바일)"), ANDROID("안드로이드 앱"), IOS("IOS 앱"), PC("PC(윈도우/맥) 프로그램"), SOLUTION("설치형/SASS 솔루션");

    @Getter
    private final String description;

    Platform(String description) {
        this.description = description;
    }

    public static List<String> toList() {
        return Arrays.stream(Platform.values())
                .map(Platform::getDescription)
                .collect(Collectors.toList());
    }

    public static Platform of(String source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }

        for (Platform p : Platform.values()) {
            if (p.description.equals(source)) {
                return p;
            }
        }

        throw new IllegalArgumentException("일치하는 경력 목록이 없습니다.");
    }

}
