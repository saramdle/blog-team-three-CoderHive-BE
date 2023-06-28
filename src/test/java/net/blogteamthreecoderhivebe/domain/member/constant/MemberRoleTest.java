package net.blogteamthreecoderhivebe.domain.member.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRoleTest {

    @DisplayName("게스트인지 아닌지 확인한다.")
    @Test
    void isGuest() {
        assertThat(MemberRole.GUEST.isGuest()).isTrue();
        assertThat(MemberRole.USER.isGuest()).isFalse();
    }
}
