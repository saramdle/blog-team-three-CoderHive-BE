package net.blogteamthreecoderhivebe.domain.post.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {
    @Test
    void builderDefaultTest() {
        Post post = Post.builder().build();
        assertThat(post.getHearts()).isNotNull();
        assertThat(post.getRecruitJobs()).isNotNull();
    }

    @Test
    void getPlatformList() {
        Post post1 = Post.builder().platforms("플랫폼1").build();
        Post post2 = Post.builder().platforms("플랫폼1&플랫폼2").build();

        List<String> platformList1 = post1.getPlatformList();
        List<String> platformList2 = post2.getPlatformList();

        assertThat(platformList1).hasSize(1);
        assertThat(platformList2).hasSize(2);
        assertThat(platformList1).containsExactly("플랫폼1");
        assertThat(platformList2).containsExactly("플랫폼1","플랫폼2");
    }
}
