package net.blogteamthreecoderhivebe.domain.post.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostCategoryTest {

    @DisplayName("카테고리 조회 성공")
    @CsvSource({
            "project, PROJECT",
            "study, STUDY",
    })
    @ParameterizedTest
    void findCategory(String key, PostCategory expected) {
        PostCategory category = PostCategory.find(key);
        assertThat(category).isEqualTo(expected);
    }

    @DisplayName("존재하지 않은 카테고리 조회 시 예외 발생")
    @Test
    void findCategoryEx() {
        assertThatThrownBy(() -> PostCategory.find("sttuuddy"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카테고리를 찾을 수 없습니다.");
    }
}
