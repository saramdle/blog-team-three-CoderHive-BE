package net.blogteamthreecoderhivebe.domain.member.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberLevelTest {
    @DisplayName("레벨 조회 성공")
    @CsvSource({
            "초심자, NEWBIE",
            "초보, BEGINNER",
            "중수, INTERMEDIATE",
            "고수, EXPERT",
            "구루, MASTER"
    })
    @ParameterizedTest
    void findLevel(String description, MemberLevel expected) {
        MemberLevel level = MemberLevel.find(description);
        assertThat(level).isEqualTo(expected);
    }

    @DisplayName("존재하지 않은 레벨을 조회 시 예외 발생")
    @Test
    void findLevelEx() {
        assertThatThrownBy(() -> MemberLevel.find("신"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("레벨을 찾을 수 없습니다.");
    }
}
