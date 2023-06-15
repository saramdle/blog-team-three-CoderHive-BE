package net.blogteamthreecoderhivebe.domain.post.service;

import net.blogteamthreecoderhivebe.domain.info.service.SkillService;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberRepository;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentSkill;
import net.blogteamthreecoderhivebe.domain.post.repository.PostRepository;
import net.blogteamthreecoderhivebe.domain.post.repository.RecruitmentSkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class RecruitmentSkillServiceTest {
    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired RecruitmentSkillRepository recruitmentSkillRepository;
    @Autowired SkillService skillService;
    @Autowired RecruitmentSkillService recruitmentSkillService;

    Post post;

    @BeforeEach
    void setUp() {
        Member member = memberRepository.save(
                Member.builder()
                        .email("test@test.com")
                        .build()
        );
        post = postRepository.save(
                Post.builder()
                        .member(member)
                        .build()
        );
    }

    @DisplayName("모집 기술 저장 성공")
    @Test
    void save() {
        // given
        List<Long> skillIds = List.of(1L, 2L, 3L);

        // when
        recruitmentSkillService.save(skillIds, post);

        // then
        List<RecruitmentSkill> findRecruitmentSkills = recruitmentSkillRepository.findAll();
        assertThat(findRecruitmentSkills).hasSize(3);
    }

    @DisplayName("게시글의 id로 해당 게시글에서 모집하는 기술의 detail 목록을 조회")
    @Test
    void findRecruitSkillDetails() {
        // given
        List<Long> skillIds = List.of(1L, 2L);
        recruitmentSkillService.save(skillIds, post);

        // when
        List<String> skillDetails = recruitmentSkillService.findRecruitSkillDetails(post.getId());

        // then
        String skillDetail1 = skillService.findOne(skillIds.get(0)).getDetail();
        String skillDetail2 = skillService.findOne(skillIds.get(1)).getDetail();
        assertThat(skillDetails.get(0)).isEqualTo(skillDetail1);
        assertThat(skillDetails.get(1)).isEqualTo(skillDetail2);
    }
}
