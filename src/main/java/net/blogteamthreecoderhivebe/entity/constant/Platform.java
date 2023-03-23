package net.blogteamthreecoderhivebe.entity.constant;

import lombok.Getter;

public enum Platform {
    NON("미정(논의 후 확정)"), WEB("반응형 웹(PC/모바일)"), ANDROID("안드로이드 앱"), IOS("IOS 앱"), PC("PC(윈도우/맥) 프로그램"), SOLUTION("설치형/SASS 솔루션");

    @Getter
    private final String description;

    Platform(String description) {
        this.description = description;
    }

}
