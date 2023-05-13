package net.blogteamthreecoderhivebe.domain.post.service;

import net.blogteamthreecoderhivebe.domain.info.repository.JobRepository;
import net.blogteamthreecoderhivebe.domain.info.repository.LocationRepository;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberRepository;
import net.blogteamthreecoderhivebe.domain.post.dto.request.RecruitmentJobRequestDto;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;
import net.blogteamthreecoderhivebe.domain.post.repository.PostRepository;
import net.blogteamthreecoderhivebe.domain.post.repository.RecruitmentJobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class RecruitmentJobServiceTest {
    @Autowired JobRepository jobRepository;
    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired LocationRepository locationRepository;
    @Autowired RecruitmentJobService recruitmentJobService;
    @Autowired RecruitmentJobRepository recruitmentJobRepository;

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

    @DisplayName("모집 직무 저장 성공")
    @Test
    void save() {
        // given
        List<RecruitmentJobRequestDto.Save> dtos = new ArrayList<>();
        dtos.add(new RecruitmentJobRequestDto.Save(1L, 1));
        dtos.add(new RecruitmentJobRequestDto.Save(2L, 2));
        dtos.add(new RecruitmentJobRequestDto.Save(3L, 3));

        // when
        recruitmentJobService.save(dtos, post);

        // then
        List<RecruitmentJob> findRecruitmentJobs = recruitmentJobRepository.findAll();
        assertThat(findRecruitmentJobs).hasSize(3);
    }
}
