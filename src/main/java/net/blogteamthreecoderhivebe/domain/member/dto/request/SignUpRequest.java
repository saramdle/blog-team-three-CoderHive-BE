package net.blogteamthreecoderhivebe.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static net.blogteamthreecoderhivebe.global.validation.ValidationGroups.NotEmptyGroup;
import static net.blogteamthreecoderhivebe.global.validation.ValidationGroups.PatternCheckGroup;

public record SignUpRequest(
        @NotBlank(message = "닉네임은 필수 입력값입니다.", groups = NotEmptyGroup.class)
        @Pattern(regexp = "[가-힣a-zA-Z0-9]{2,10}$", message = "닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.", groups = PatternCheckGroup.class)
        String nickname,

        @NotBlank(message = "직무는 필수 입력값입니다.", groups = NotEmptyGroup.class)
        @Pattern(regexp = "[0-9]{1,10}$", message = "직무는 1 이상의 1~10자리 숫자여야 합니다.", groups = PatternCheckGroup.class)
        String jobId,

        @NotBlank(message = "레벨은 필수 입력값입니다.", groups = NotEmptyGroup.class)
        @Pattern(regexp = "[가-힣]{1,10}$", message = "레벨은 정해진 description을 만족해야 합니다.", groups = PatternCheckGroup.class)
        String level,

        @NotBlank(message = "경력은 필수 입력값입니다.", groups = NotEmptyGroup.class)
        @Pattern(regexp = "[가-힣0-9~ ]{1,10}$", message = "경력은 정해진 description을 만족해야 합니다.", groups = PatternCheckGroup.class)
        String career
) {
}
