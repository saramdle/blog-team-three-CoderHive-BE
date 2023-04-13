package net.blogteamthreecoderhivebe.repository.querydsl;

import net.blogteamthreecoderhivebe.entity.PostJob;

import java.util.List;

public interface PostJobRepositoryCustom {
    List<PostJob> getPostJobByPostId(Long postId);
}
