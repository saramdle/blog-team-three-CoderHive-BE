package net.blogteamthreecoderhivebe.repository.querydsl;

import net.blogteamthreecoderhivebe.entity.Post;
import net.blogteamthreecoderhivebe.entity.constant.ApplyResult;

import java.util.List;
import java.util.Map;

public interface PostRepositoryCustom {
    Map<ApplyResult, List<Post>> memberApplyPost(Long memberId);
}
