package net.blogteamthreecoderhivebe.domain.post.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostStatusTest {
    @DisplayName("모집상태 조회 성공")
    @CsvSource({
            "모집중, HIRING",
            "모집 완료, CLOSED",
    })
    @ParameterizedTest
    void findStatus(String description, PostStatus expected) {
        PostStatus status = PostStatus.find(description);
        assertThat(status).isEqualTo(expected);
    }

    @DisplayName("존재하지 않은 모집상태 조회 시 예외 발생")
    @Test
    void findStatusEx() {
        assertThatThrownBy(() -> PostStatus.find("모집 없음"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("모집 상태를 찾을 수 없습니다.");
    }
}
