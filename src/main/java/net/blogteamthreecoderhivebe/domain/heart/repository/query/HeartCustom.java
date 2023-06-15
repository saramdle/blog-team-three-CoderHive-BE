package net.blogteamthreecoderhivebe.domain.heart.repository.query;

import net.blogteamthreecoderhivebe.domain.post.entity.Post;

import java.util.List;

public interface HeartCustom {
    List<Post> findHeartPosts(Long memberId);
}
