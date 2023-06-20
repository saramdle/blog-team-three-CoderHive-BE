package net.blogteamthreecoderhivebe.domain.post.service;

import net.blogteamthreecoderhivebe.domain.info.service.SkillService;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberRepository;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitSkill;
import net.blogteamthreecoderhivebe.domain.post.repository.PostRepository;
import net.blogteamthreecoderhivebe.domain.post.repository.RecruitSkillRepository;
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
class RecruitSkillServiceTest {

    @Autowired RecruitSkillService recruitSkillService;

    @Autowired SkillService skillService;
    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired RecruitSkillRepository recruitSkillRepository;

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
        recruitSkillService.save(skillIds, post);

        // then
        List<RecruitSkill> findRecruitSkills = recruitSkillRepository.findAll();
        assertThat(findRecruitSkills).hasSize(3);
    }

    @DisplayName("게시글의 id로 해당 게시글에서 모집하는 기술의 detail 목록을 조회")
    @Test
    void findRecruitSkillDetails() {
        // given
        List<Long> skillIds = List.of(1L, 2L);
        recruitSkillService.save(skillIds, post);

        // when
        List<String> skillDetails = recruitSkillService.findRecruitSkillDetails(post.getId());

        // then
        String skillDetail1 = skillService.findOne(skillIds.get(0)).getDetail();
        String skillDetail2 = skillService.findOne(skillIds.get(1)).getDetail();
        assertThat(skillDetails.get(0)).isEqualTo(skillDetail1);
        assertThat(skillDetails.get(1)).isEqualTo(skillDetail2);
    }
}
