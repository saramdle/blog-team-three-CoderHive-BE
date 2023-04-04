package net.blogteamthreecoderhivebe.repository.querydsl;

import net.blogteamthreecoderhivebe.entity.Post;
import net.blogteamthreecoderhivebe.entity.PostJob;
import net.blogteamthreecoderhivebe.entity.constant.PostCategory;
import net.blogteamthreecoderhivebe.entity.constant.PostStatus;
import net.blogteamthreecoderhivebe.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DisplayName("Post 레포지토리 확인")
@Transactional
@SpringBootTest
public class PostRepositoryCustomImplTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void postPageTest() {
        PageRequest page = PageRequest.of(0, 20);
        Page<Post> allPost = postRepository.getAllPost(PostCategory.PROJECT, List.of((long)1, (long)2, (long)3), List.of((long)1), PostStatus.HIRING, page);
        for (Post post : allPost) {
            System.out.println(post.getLocation().getRegion());
            List<PostJob> postJobs = post.getPostJobs();
            for (PostJob postJob : postJobs) {
                System.out.println(postJob.getJob().getDetail());
            }
        }
    }
}