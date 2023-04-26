package net.blogteamthreecoderhivebe.domain.member.service;

import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @DisplayName("신규 회원이면 true, 기존 회원이면 false 반환 확인")
    @CsvSource({
            "new@naver.com, true",
            "exist@naver.com, false"
    })
    @ParameterizedTest
    void checkNewMemberOrExistMember(String email, Boolean expected) {
        memberRepository.save(Member.builder()
                .email("exist@naver.com")
                .nickname("nickname")
                .build()
        );

        Boolean result = memberService.isNewMember(email);

        assertThat(result).isEqualTo(expected);
    }
}
