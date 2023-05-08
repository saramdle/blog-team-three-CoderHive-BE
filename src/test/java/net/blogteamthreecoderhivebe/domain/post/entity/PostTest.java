package net.blogteamthreecoderhivebe.domain.post.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {
    @Test
    void builderDefaultTest() {
        Post post = Post.builder().build();
        assertThat(post.getHearts()).isNotNull();
        assertThat(post.getRecruitmentJobs()).isNotNull();
    }
}
