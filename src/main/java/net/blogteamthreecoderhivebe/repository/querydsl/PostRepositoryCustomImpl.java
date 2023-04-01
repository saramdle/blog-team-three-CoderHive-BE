package net.blogteamthreecoderhivebe.repository.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.entity.Post;
import net.blogteamthreecoderhivebe.entity.constant.ApplyResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.blogteamthreecoderhivebe.entity.QMemberApply.memberApply;
import static net.blogteamthreecoderhivebe.entity.QPost.post;
import static net.blogteamthreecoderhivebe.entity.QPostJob.postJob;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Map<ApplyResult, List<Post>> memberApplyPost(Long memberId) {
        List<Tuple> result = queryFactory
                .select(post, memberApply.applyResult)
                .from(memberApply)
                .join(memberApply.postJob, postJob)
                .join(postJob.post, post)
                .where(
                        memberApply.member.id.eq(memberId)
                )
                .fetch();

        Map<ApplyResult, List<Post>> posts = new HashMap<>();

        List<Post> passPost = new ArrayList<>();
        List<Post> nonPost = new ArrayList<>();
        for (Tuple tuple : result) {
            System.out.println(tuple.get(memberApply.applyResult));
            if (tuple.get(memberApply.applyResult) == ApplyResult.NON) {
                nonPost.add(tuple.get(post));
            } else if (tuple.get(memberApply.applyResult) == ApplyResult.PASS) {
                passPost.add(tuple.get(post));
            }
        }
        posts.put(ApplyResult.NON, nonPost);
        posts.put(ApplyResult.PASS, passPost);
        return posts;
    }
}
