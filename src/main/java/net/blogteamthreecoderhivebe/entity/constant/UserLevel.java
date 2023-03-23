package net.blogteamthreecoderhivebe.entity.constant;

import lombok.Getter;

public enum UserLevel {
    NEWBIE("초심자"), BEGINNER("초보"), INTERMEDIATE("중수"), EXPERT("고수"), MASTER("구루");
    @Getter
    private final String description;

    UserLevel(String description) {
        this.description = description;
    }


}
