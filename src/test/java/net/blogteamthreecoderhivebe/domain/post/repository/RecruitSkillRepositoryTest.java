package net.blogteamthreecoderhivebe.domain.post.repository;

import jakarta.transaction.Transactional;
import net.blogteamthreecoderhivebe.domain.info.entity.Skill;
import net.blogteamthreecoderhivebe.domain.info.repository.SkillRepository;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitSkill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class RecruitSkillRepositoryTest {

    @Autowired RecruitSkillRepository recruitSkillRepository;

    @Autowired PostRepository postRepository;
    @Autowired SkillRepository skillRepository;

    @DisplayName("게시글 id로 해당 게시글에서 모집하는 기술 목록을 조회")
    @Test
    void findSkillsEqPostId() {
        // given
        Skill skill1 = skillRepository.findById(1L).orElseThrow();
        Skill skill2 = skillRepository.findById(2L).orElseThrow();

        Post post = Post.builder().build();
        post.addRecruitSkill(RecruitSkill.from(skill1));
        post.addRecruitSkill(RecruitSkill.from(skill2));
        postRepository.save(post);

        // when
        List<Skill> findSkills = recruitSkillRepository.findSkills(post.getId());

        // then
        assertThat(findSkills).hasSize(2);
        assertThat(findSkills).containsExactly(skill1, skill2);
    }
}
