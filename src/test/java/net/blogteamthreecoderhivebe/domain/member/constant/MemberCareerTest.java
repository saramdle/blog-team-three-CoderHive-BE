package net.blogteamthreecoderhivebe.domain.member.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberCareerTest {
    @DisplayName("경력 조회 성공")
    @CsvSource({
            "미지정, NON",
            "1~3년차, ASSOCIATE",
            "5~10년차, SENIOR",
            "10년차 이상, PRINCIPAL"
    })
    @ParameterizedTest
    void findCareer(String description, MemberCareer expected) {
        MemberCareer result = MemberCareer.find(description);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("존재하지 않은 경력을 조회 시 예외 발생")
    @Test
    void findCareerEx() {
        assertThatThrownBy(() -> MemberCareer.find("20년차"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("경력을 찾을 수 없습니다.");
    }
}
