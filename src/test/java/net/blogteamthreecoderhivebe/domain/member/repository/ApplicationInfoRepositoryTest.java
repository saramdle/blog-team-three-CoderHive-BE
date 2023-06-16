package net.blogteamthreecoderhivebe.domain.member.repository;

import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.member.entity.ApplicationInfo;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitJob;
import net.blogteamthreecoderhivebe.domain.post.repository.PostRepository;
import net.blogteamthreecoderhivebe.domain.post.repository.RecruitJobRepository;
import net.blogteamthreecoderhivebe.global.config.TestJpaConfig;
import net.blogteamthreecoderhivebe.global.config.TestQueryDslConfig;
import org.assertj.core.api.Assertions;
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

    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired RecruitJobRepository recruitJobRepository;

    Member member;
    RecruitJob recruitJob;

    @BeforeEach
    void setUp() {
        member = memberRepository.save(
                Member.builder().email("test@test.com").build()
        );

        recruitJob = recruitJobRepository.save(
                RecruitJob.builder().build()
        );
    }

    @DisplayName("memberId로 지원 정보 목록을 조회한다.")
    @Test
    void findApplyInfosByMemberId() {
        // given
        ApplicationInfo applicationInfo = ApplicationInfo.of(member, recruitJob);
        applicationInfoRepository.save(applicationInfo);

        // when
        List<ApplicationInfo> applicationInfos = applicationInfoRepository.findByMember_Id(member.getId());

        // then
        assertThat(applicationInfos).hasSize(1);
    }

    @DisplayName("회원이 해당 모집 직무에 지원한 이력이 없으면 지원 결과가 빈값이다.")
    @Test
    void findApplyResultEmpty() {
        Optional<ApplicationResult> applyResult = applicationInfoRepository.findApplyResult(member, recruitJob);
        assertThat(applyResult).isEmpty();
    }

    @DisplayName("회원이 해당 모집 직무에 지원한 이력이 있으면 지원 결과가 반환된다.")
    @Test
    void findApplyResult() {
        // given
        ApplicationInfo applicationInfo = ApplicationInfo.of(member, recruitJob);
        applicationInfoRepository.save(applicationInfo);

        // when
        Optional<ApplicationResult> applyResult = applicationInfoRepository.findApplyResult(member, recruitJob);

        // then
        assertThat(applyResult).isPresent();
        assertThat(applyResult).contains(ApplicationResult.APPLY);
    }

    @DisplayName("모집직무에 합격해 게시글에 참여하는 모든 회원을 조회한다.")
    @Test
    void findPassMembers() {
        // given
        Post post = Post.builder().build();
        post.addRecruitJob(recruitJob);
        postRepository.save(post);

        ApplicationInfo applicationInfo = ApplicationInfo.of(member, recruitJob);
        applicationInfoRepository.save(applicationInfo);

        applicationInfo.modifyResultToPass(); // 합격 처리

        // when
        List<Member> passMembers = applicationInfoRepository.findPassMembers(post);

        // then
        Assertions.assertThat(passMembers).hasSize(1);
        Assertions.assertThat(passMembers).containsExactly(member);
    }
}
