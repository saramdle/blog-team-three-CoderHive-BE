package net.blogteamthreecoderhivebe.repository.querydsl;

import net.blogteamthreecoderhivebe.entity.Post;
import net.blogteamthreecoderhivebe.entity.constant.ApplyResult;
import net.blogteamthreecoderhivebe.entity.constant.PostCategory;
import net.blogteamthreecoderhivebe.entity.constant.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PostRepositoryCustom {
    Map<ApplyResult, List<Post>> memberApplyPost(Long memberId);

    Page<Post> getAllPost(PostCategory postCategory, List<Long> regions, List<Long> jobs, PostStatus status, Pageable pageable);
}
