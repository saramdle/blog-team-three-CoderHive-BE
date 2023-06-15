package net.blogteamthreecoderhivebe.domain.heart.repository.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;

import java.util.List;

import static net.blogteamthreecoderhivebe.domain.heart.entity.QHeart.heart;

@RequiredArgsConstructor
public class HeartCustomImpl implements HeartCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> findHeartPosts(Long memberId) {
        return queryFactory
                .select(heart.post)
                .from(heart)
                .where(eqMemberId(memberId))
                .fetch();
    }

    private BooleanExpression eqMemberId(Long memberId) {
        return heart.member.id.eq(memberId);
    }
}
