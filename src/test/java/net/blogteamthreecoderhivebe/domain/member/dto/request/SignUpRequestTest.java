package net.blogteamthreecoderhivebe.domain.member.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

class SignUpRequestTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("회원가입 DTO 필드값 검증(Validation)")
    @Test
    void notEmpty_validation() {
        SignUpRequest signUpRequest01 = new SignUpRequest("Nickname", "4", "초보", "미지정");
        SignUpRequest signUpRequest02 = new SignUpRequest("Nickname", "ab", "초보", "미지정");
        Set<ConstraintViolation<SignUpRequest>> violations01 = validator.validate(signUpRequest01);
        Set<ConstraintViolation<SignUpRequest>> violations02 = validator.validate(signUpRequest02);
        for (ConstraintViolation<SignUpRequest> violation : violations01) {
            System.err.println(violation.getMessage());
        }
        for (ConstraintViolation<SignUpRequest> violation : violations02) {
            System.err.println(violation.getMessage());
        }
    }

}