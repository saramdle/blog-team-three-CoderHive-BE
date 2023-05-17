package net.blogteamthreecoderhivebe.domain.post.repository.querydsl;

import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PostCustom {
    Map<ApplicationResult, List<Post>> memberApplyPost(Long memberId);

    Page<Post> getAllPost(PostSearchCond searchCond, Pageable pageable);
}
