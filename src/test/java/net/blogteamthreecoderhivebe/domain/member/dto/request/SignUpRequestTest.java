package net.blogteamthreecoderhivebe.domain.member.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SignUpRequestTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("회원가입 시 닉네임 입력 - 빈값인지 검증(Validation)")
    @Test
    void empty_Nickname_validation() {
        SignUpRequest signUpRequest02 = new SignUpRequest("", "1", "초보", "미지정");
        Set<ConstraintViolation<SignUpRequest>> violations02 = validator.validate(signUpRequest02);

        violations02
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("닉네임은 필수 입력값입니다.");
                });
    }

    @DisplayName("회원가입 시 직무 입력 - 빈값인지 검증(Validation)")
    @Test
    void empty_Job_validation() {
        SignUpRequest signUpRequest02 = new SignUpRequest("Nickname", "", "초보", "미지정");
        Set<ConstraintViolation<SignUpRequest>> violations02 = validator.validate(signUpRequest02);

        violations02
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("직무는 필수 입력값입니다.");
                });
    }

    @DisplayName("회원가입 시 레벨 입력 - 빈값인지 검증(Validation)")
    @Test
    void empty_Level_validation() {
        SignUpRequest signUpRequest02 = new SignUpRequest("Nickname", "1", "", "미지정");
        Set<ConstraintViolation<SignUpRequest>> violations02 = validator.validate(signUpRequest02);

        violations02
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("레벨은 필수 입력값입니다.");
                });
    }

    @DisplayName("회원가입 시 경력 입력 - 빈값인지 검증(Validation)")
    @Test
    void empty_Career_validation() {
        SignUpRequest signUpRequest02 = new SignUpRequest("Nickname", "1", "초보", "");
        Set<ConstraintViolation<SignUpRequest>> violations02 = validator.validate(signUpRequest02);

        violations02
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("경력은 필수 입력값입니다.");
                });
    }

    @DisplayName("회원가입 시 닉네임 입력 - 표현식 검증(Validation)")
    @Test
    void notEmpty_Nickname_validation() {
        SignUpRequest signUpRequest02 = new SignUpRequest("())", "1", "초보", "미지정");
        Set<ConstraintViolation<SignUpRequest>> violations02 = validator.validate(signUpRequest02);

        violations02
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.");
                });
    }

    @DisplayName("회원가입 시 직무 입력 - 표현식 검증(Validation)")
    @Test
    void notEmpty_Job_validation() {
        SignUpRequest signUpRequest02 = new SignUpRequest("Nickname", "ab", "초보", "미지정");
        Set<ConstraintViolation<SignUpRequest>> violations02 = validator.validate(signUpRequest02);

        violations02
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("직무는 1 이상의 1~10자리 숫자여야 합니다.");
                });
    }

    @DisplayName("회원가입 시 레벨 입력 - 표현식 검증(Validation)")
    @Test
    void notEmpty_Level_validation() {
        SignUpRequest signUpRequest02 = new SignUpRequest("Nickname", "1", "나이스", "미지정");
        Set<ConstraintViolation<SignUpRequest>> violations02 = validator.validate(signUpRequest02);

        violations02
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("레벨은 정해진 description을 만족해야 합니다.");
                });
    }

    @DisplayName("회원가입 시 경력 입력 - 표현식 검증(Validation)")
    @Test
    void notEmpty_Career_validation() {
        SignUpRequest signUpRequest02 = new SignUpRequest("Nickname", "1", "초보", "나이스");
        Set<ConstraintViolation<SignUpRequest>> violations02 = validator.validate(signUpRequest02);

        violations02
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("경력은 정해진 description을 만족해야 합니다.");
                });
    }

}
