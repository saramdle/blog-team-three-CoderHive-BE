package net.blogteamthreecoderhivebe.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.entity.PostJob;

import java.util.List;

import static net.blogteamthreecoderhivebe.entity.QJob.job;
import static net.blogteamthreecoderhivebe.entity.QPostJob.postJob;

@RequiredArgsConstructor
public class PostJobRepositoryCustomImpl implements PostJobRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostJob> getPostJobByPostId(Long postId) {
        return queryFactory
                .selectFrom(postJob)
                .join(postJob.job, job).fetchJoin()
                .where(postJob.post.id.eq(postId))
                .fetch();
    }
}
