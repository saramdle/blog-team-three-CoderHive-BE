package net.blogteamthreecoderhivebe.domain.member.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import net.blogteamthreecoderhivebe.global.validation.ValidationGroups.NotEmptyGroup;
import net.blogteamthreecoderhivebe.global.validation.ValidationGroups.PatternCheckGroup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("회원가입 입력 검증(Validation) 테스트")
class SignUpRequestTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("정상입력 성공")
    @Test
    void signUp_validation() {
        SignUpRequest signUpRequest = new SignUpRequest("nickname", "1", "초보", "미지정");
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest, NotEmptyGroup.class, PatternCheckGroup.class);
        assertThat(violations).isEmpty();
    }

    @Nested
    @DisplayName("빈값 검증")
    class notEmptyGroup_test {
        @DisplayName("닉네임 입력")
        @Test
        void empty_Nickname_validation() {
            SignUpRequest signUpRequest = new SignUpRequest("", "1", "초보", "미지정");
            Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest, NotEmptyGroup.class);

            assertThat(violations)
                    .extracting(ConstraintViolation::getMessage)
                    .containsOnly("닉네임은 필수 입력값입니다.");
        }

        @DisplayName("직무 입력")
        @Test
        void empty_Job_validation() {
            SignUpRequest signUpRequest = new SignUpRequest("Nickname", "", "초보", "미지정");
            Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest, NotEmptyGroup.class);

            assertThat(violations)
                    .extracting(ConstraintViolation::getMessage)
                    .containsOnly("직무는 필수 입력값입니다.");
        }

        @DisplayName("레벨 입력")
        @Test
        void empty_Level_validation() {
            SignUpRequest signUpRequest = new SignUpRequest("Nickname", "1", "", "미지정");
            Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest, NotEmptyGroup.class);

            assertThat(violations)
                    .extracting(ConstraintViolation::getMessage)
                    .containsOnly("레벨은 필수 입력값입니다.");
        }

        @DisplayName("경력 입력")
        @Test
        void empty_Career_validation() {
            SignUpRequest signUpRequest = new SignUpRequest("Nickname", "1", "초보", "");
            Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest, NotEmptyGroup.class);

            assertThat(violations)
                    .extracting(ConstraintViolation::getMessage)
                    .containsOnly("경력은 필수 입력값입니다.");
        }
    }

    @Nested
    @DisplayName("표현식 검증")
    class patternCheckGroup_test {
        @DisplayName("닉네임 입력")
        @Test
        void notEmpty_Nickname_validation() {
            SignUpRequest signUpRequest = new SignUpRequest("())", "1", "초보", "미지정");
            Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest, PatternCheckGroup.class);

            assertThat(violations)
                    .extracting(ConstraintViolation::getMessage)
                    .containsOnly("닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.");
        }

        @DisplayName("직무 입력")
        @Test
        void notEmpty_Job_validation() {
            SignUpRequest signUpRequest = new SignUpRequest("Nickname", "ab", "초보", "미지정");
            Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest, PatternCheckGroup.class);

            assertThat(violations)
                    .extracting(ConstraintViolation::getMessage)
                    .containsOnly("직무는 1 이상의 1~10자리 숫자여야 합니다.");
        }

        @DisplayName("레벨 입력")
        @Test
        void notEmpty_Level_validation() {
            SignUpRequest signUpRequest = new SignUpRequest("Nickname", "1", "level", "미지정");
            Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest, PatternCheckGroup.class);

            assertThat(violations)
                    .extracting(ConstraintViolation::getMessage)
                    .containsOnly("레벨은 정해진 description을 만족해야 합니다.");
        }

        @DisplayName("경력 입력")
        @Test
        void notEmpty_Career_validation() {
            SignUpRequest signUpRequest = new SignUpRequest("Nickname", "1", "초보", "career");
            Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest, PatternCheckGroup.class);

            assertThat(violations)
                    .extracting(ConstraintViolation::getMessage)
                    .containsOnly("경력은 정해진 description을 만족해야 합니다.");
        }
    }
}
