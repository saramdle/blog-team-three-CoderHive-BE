package net.blogteamthreecoderhivebe.domain.post.repository;

import net.blogteamthreecoderhivebe.domain.info.service.JobService;
import net.blogteamthreecoderhivebe.domain.info.service.LocationService;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitJob;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PostRepositoryTest {

    @Autowired PostRepository postRepository;

    @Autowired JobService jobService;
    @Autowired LocationService locationService;

    final Pageable pageable = PageRequest.of(0, 10);

    @DisplayName("카테고리로 게시글 검색 성공")
    @Test
    void findPostsEqCategory() {
        // given
        Post studyPost = Post.builder()
                .postCategory(PostCategory.STUDY)
                .build();

        Post projectPost1 = Post.builder()
                .postCategory(PostCategory.PROJECT)
                .build();
        Post projectPost2 = Post.builder()
                .postCategory(PostCategory.PROJECT)
                .build();

        postRepository.save(studyPost);
        postRepository.save(projectPost1);
        postRepository.save(projectPost2);

        // when
        Page<Post> findStudyPosts = postRepository.findPosts(PostCategory.STUDY, null, null, null, pageable);
        Page<Post> findProjectPosts = postRepository.findPosts(PostCategory.PROJECT, null, null, null, pageable);

        // then
        assertThat(findStudyPosts.getTotalElements()).isEqualTo(1);
        assertThat(findProjectPosts.getTotalElements()).isEqualTo(2);
    }

    @DisplayName("모집 상태로 게시글 검색 성공")
    @Test
    void findPostsEqStatus() {
        // given
        Post hiringPost = Post.builder()
                .postStatus(PostStatus.HIRING)
                .build();
        Post closedPost = Post.builder()
                .postStatus(PostStatus.CLOSED)
                .build();

        postRepository.save(hiringPost);
        postRepository.save(closedPost);

        // when
        Page<Post> findHiringPosts = postRepository.findPosts(null, PostStatus.HIRING, null, null, pageable);
        Page<Post> findClosedPosts = postRepository.findPosts(null, PostStatus.CLOSED, null, null, pageable);

        // then
        assertThat(findHiringPosts.getTotalElements()).isEqualTo(1);
        assertThat(findClosedPosts.getTotalElements()).isEqualTo(1);
    }

    @DisplayName("지역으로 게시글 검색 성공")
    @Test
    void findPostsInLocation() {
        // given
        Post post1 = Post.builder()
                .location(locationService.findOne(1L))
                .build();
        Post post2 = Post.builder()
                .location(locationService.findOne(2L))
                .build();

        postRepository.save(post1);
        postRepository.save(post2);

        // when
        Page<Post> findPostsLocation1 = postRepository.findPosts(null, null, List.of(1L), null, pageable);
        Page<Post> findPostsLocation2 = postRepository.findPosts(null, null, List.of(2L), null, pageable);
        Page<Post> findPostsLocationAll = postRepository.findPosts(null, null, List.of(1L, 2L), null, pageable);

        // then
        assertThat(findPostsLocation1.getTotalElements()).isEqualTo(1);
        assertThat(findPostsLocation2.getTotalElements()).isEqualTo(1);
        assertThat(findPostsLocationAll.getTotalElements()).isEqualTo(2);
    }

    @DisplayName("모집 직무로 게시글 검색 성공")
    @Test
    void findPostsInJob() {
        // given
        Post post1 = Post.builder().build();
        Post post2 = Post.builder().build();

        RecruitJob recruitJob1 = RecruitJob.builder().job(jobService.findOne(1L)).build();
        RecruitJob recruitJob2 = RecruitJob.builder().job(jobService.findOne(2L)).build();
        post1.addRecruitJob(recruitJob1);
        post2.addRecruitJob(recruitJob2);

        postRepository.save(post1);
        postRepository.save(post2);

        // when
        Page<Post> findPostsJob1 = postRepository.findPosts(null, null, null, List.of(1L), pageable);
        Page<Post> findPostsJob2 = postRepository.findPosts(null, null, null, List.of(2L), pageable);
        Page<Post> findPostsJobAll = postRepository.findPosts(null, null, null, List.of(1L,2L), pageable);

        // then
        assertThat(findPostsJob1.getTotalElements()).isEqualTo(1);
        assertThat(findPostsJob2.getTotalElements()).isEqualTo(1);
        assertThat(findPostsJobAll.getTotalElements()).isEqualTo(2);
    }
}
