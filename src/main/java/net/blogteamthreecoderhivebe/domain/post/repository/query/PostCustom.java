package net.blogteamthreecoderhivebe.domain.post.repository.query;

import net.blogteamthreecoderhivebe.domain.member.constant.ApplyResult;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PostCustom {

    Map<ApplyResult, List<Post>> memberApplyPost(Long memberId);

    Page<Post> findPosts(PostCategory category, PostStatus status, List<Long> locations, List<Long> jobs, Pageable pageable);
}
