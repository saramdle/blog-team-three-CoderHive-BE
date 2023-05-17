package net.blogteamthreecoderhivebe.domain.post.repository.querydsl;

import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;

import java.util.List;

public record PostSearchCond(
        PostCategory postCategory,
        List<Long> locationIds,
        List<Long> jobIds,
        PostStatus postStatus
) {
}
