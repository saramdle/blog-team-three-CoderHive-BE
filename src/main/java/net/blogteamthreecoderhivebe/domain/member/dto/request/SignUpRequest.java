package net.blogteamthreecoderhivebe.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SignUpRequest(
        @NotBlank(message = "닉네임은 필수 입력값입니다.")
        @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$", message = "닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
        String nickname,
        @NotBlank(message = "직무는 필수 입력값입니다.")
        @Pattern(regexp = "^[0-9]{1,10}$", message = "직무는 1 이상의 1~10자리 숫자여야 합니다.")
        String jobId,
        @NotBlank(message = "레벨은 필수 입력값입니다.")
        @Pattern(regexp = "^[가-힣a-zA-Z0-9-_~]{1,20}$", message = "레벨은 정해진 description을 만족해야 합니다.")
        String level,
        @NotBlank(message = "경력은 필수 입력값입니다.")
        @Pattern(regexp = "^[가-힣a-zA-Z0-9-_~]{1,20}$", message = "경력은 정해진 description을 만족해야 합니다.")
        String career
) {
}