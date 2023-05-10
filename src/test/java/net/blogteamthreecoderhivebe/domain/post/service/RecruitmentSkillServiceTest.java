package net.blogteamthreecoderhivebe.domain.post.service;

import net.blogteamthreecoderhivebe.domain.info.repository.JobRepository;
import net.blogteamthreecoderhivebe.domain.info.repository.LocationRepository;
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
    @Autowired JobRepository jobRepository;
    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired LocationRepository locationRepository;
    @Autowired RecruitmentSkillService recruitmentSkillService;
    @Autowired RecruitmentSkillRepository recruitmentSkillRepository;

    Post post;

    @BeforeEach
    void setUp() {
        post = postRepository.save(Post.builder()
                .member(memberRepository.save(Member.builder()
                        .email("test@test.com")
                        .build()))
                .job(jobRepository.findById(1L).get())
                .location(locationRepository.findById(1L).get())
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
}
