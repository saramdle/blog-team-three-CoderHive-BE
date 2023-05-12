package net.blogteamthreecoderhivebe.domain.member.service;

import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import net.blogteamthreecoderhivebe.domain.info.repository.JobRepository;
import net.blogteamthreecoderhivebe.domain.member.dto.SignUpDto;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static net.blogteamthreecoderhivebe.domain.member.constant.MemberCareer.NON;
import static net.blogteamthreecoderhivebe.domain.member.constant.MemberLevel.BEGINNER;
import static net.blogteamthreecoderhivebe.domain.member.constant.MemberRole.GUEST;
import static net.blogteamthreecoderhivebe.domain.member.constant.MemberRole.USER;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    JobRepository jobRepository;

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

    @DisplayName("신규 회원의 추가정보를 업데이트하는 기능 테스트")
    @Test
    void signUp() {
        //최초 Guest 저장
        Member guestMember = Member.builder()
                .email("example@naver.com")
                .memberRole(GUEST)
                .build();
        memberRepository.save(guestMember);

        //회원가입 - 추가정보로 업데이트
        SignUpDto signUpDto = SignUpDto.builder()
                .email("example@naver.com")
                .nickname("exampleNick")
                .jobId(4)
                .memberLevel(BEGINNER)
                .memberCareer(NON)
                .build();
        memberService.signUp(signUpDto);

        //DB에 업데이트 됐는지 확인
        Member savedMember = memberRepository.findByEmail(guestMember.getEmail()).get();
        Job job = jobRepository.findById(signUpDto.jobId()).orElseThrow();

        assertThat(savedMember.getEmail()).isEqualTo(signUpDto.email());
        assertThat(savedMember.getNickname()).isEqualTo(signUpDto.nickname());
        assertThat(savedMember.getJob()).isEqualTo(job);
        assertThat(savedMember.getMemberRole()).isEqualTo(USER);
        assertThat(savedMember.getLevel()).isEqualTo(signUpDto.memberLevel());
        assertThat(savedMember.getCareer()).isEqualTo(signUpDto.memberCareer());
    }
}
