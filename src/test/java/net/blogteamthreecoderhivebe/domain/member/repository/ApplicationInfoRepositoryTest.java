package net.blogteamthreecoderhivebe.domain.member.repository;

import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.member.entity.ApplicationInfo;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;
import net.blogteamthreecoderhivebe.domain.post.repository.RecruitmentJobRepository;
import net.blogteamthreecoderhivebe.global.config.TestJpaConfig;
import net.blogteamthreecoderhivebe.global.config.TestQueryDslConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Import({TestJpaConfig.class, TestQueryDslConfig.class})
@DataJpaTest
class ApplicationInfoRepositoryTest {

    @Autowired ApplicationInfoRepository applicationInfoRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired RecruitmentJobRepository recruitmentJobRepository;

    Member member;
    RecruitmentJob recruitmentJob;

    @BeforeEach
    void setUp() {
        member = memberRepository.save(
                Member.builder().email("test@test.com").build()
        );

        recruitmentJob = recruitmentJobRepository.save(
                RecruitmentJob.builder().build()
        );
    }

    @DisplayName("memberId로 지원 정보 목록을 조회한다.")
    @Test
    void findApplyInfosByMemberId() {
        // given
        ApplicationInfo applicationInfo = ApplicationInfo.of(member, recruitmentJob);
        applicationInfoRepository.save(applicationInfo);

        // when
        List<ApplicationInfo> applicationInfos = applicationInfoRepository.findByMember_Id(member.getId());

        // then
        assertThat(applicationInfos).hasSize(1);
    }

    @DisplayName("회원이 해당 모집 직무에 지원한 이력이 없으면 지원 결과가 빈값이다.")
    @Test
    void findApplyResultEmpty() {
        Optional<ApplicationResult> applyResult = applicationInfoRepository.findApplyResult(member, recruitmentJob);
        assertThat(applyResult).isEmpty();
    }

    @DisplayName("회원이 해당 모집 직무에 지원한 이력이 있으면 지원 결과가 반환된다.")
    @Test
    void findApplyResult() {
        // given
        ApplicationInfo applicationInfo = ApplicationInfo.of(member, recruitmentJob);
        applicationInfoRepository.save(applicationInfo);

        // when
        Optional<ApplicationResult> applyResult = applicationInfoRepository.findApplyResult(member, recruitmentJob);

        // then
        assertThat(applyResult).isPresent();
        assertThat(applyResult).contains(ApplicationResult.APPLY);
    }
}
