package net.blogteamthreecoderhivebe.global.common;

import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import net.blogteamthreecoderhivebe.domain.info.entity.Location;
import net.blogteamthreecoderhivebe.domain.info.repository.JobRepository;
import net.blogteamthreecoderhivebe.domain.info.repository.LocationRepository;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberRepository;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class BaseEntityTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    LocationRepository locationRepository;

    @DisplayName("회원 등록시 생성일, 수정일을 자동으로 생성")
    @Test
    void member() {
        // given
        Member member = Member.builder().email("test@test.com").build();

        // when
        memberRepository.save(member);

        // then
        assertThat(member.getCreatedAt()).isNotNull();
        assertThat(member.getModifiedAt()).isNotNull();
    }

    @DisplayName("게시물 등록시 생성일, 수정일을 자동으로 생성")
    @Test
    void post() {
        // given
        Member member = Member.builder().email("test@test.com").build();
        memberRepository.save(member);

        Job job = jobRepository.findById(1L).orElseThrow();
        Location location = locationRepository.findById(1L).orElseThrow();

        Post post = Post.builder()
                .member(member)
                .location(location)
                .job(job)
                .build();

        Post savedPost = postRepository.save(post);

        // when
        Post findPost = postRepository.findById(savedPost.getId()).orElseThrow();
        System.out.println(findPost.getCreatedBy());

        // then
        assertThat(findPost.getCreatedAt()).isNotNull();
        assertThat(findPost.getModifiedAt()).isNotNull();
    }
}
