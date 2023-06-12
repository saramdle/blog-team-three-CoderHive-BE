package net.blogteamthreecoderhivebe.domain.post.service;

import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberRepository;
import net.blogteamthreecoderhivebe.domain.post.dto.request.PostRequestDto;
import net.blogteamthreecoderhivebe.domain.post.dto.request.RecruitmentJobRequestDto;
import net.blogteamthreecoderhivebe.domain.post.dto.response.PostResponseDto;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.repository.PostRepository;
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
class PostServiceTest {
    @Autowired PostService postService;

    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;

    Member member;

    @BeforeEach
    void setUp() {
        member = memberRepository.save(
                Member.builder()
                        .email("test@test.com")
                        .build()
        );
    }

    @DisplayName("게시글 저장에 성공하면 저장된 게시글의 id를 반환한다.")
    @Test
    void save() {
        // given
        PostRequestDto.Save request = PostRequestDto.Save.builder()
                .category("project")
                .locationId(1L)
                .skillIds(List.of(1L, 2L))
                .recruitmentJobs(List.of(new RecruitmentJobRequestDto.Save(1L, 1)))
                .myJobId(1L)
                .build();

        // when
        PostResponseDto.Save response = postService.save(request, member.getEmail());

        // then
        List<Post> posts = postRepository.findAll();
        assertThat(posts).hasSize(1);
        assertThat(posts.get(0).getId()).isEqualTo(response.postId());
    }
}
