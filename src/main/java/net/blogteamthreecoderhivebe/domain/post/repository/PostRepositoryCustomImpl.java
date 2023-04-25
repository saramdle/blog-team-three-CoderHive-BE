package net.blogteamthreecoderhivebe.domain.post.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplyResult;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.blogteamthreecoderhivebe.domain.info.entity.QLocation.location;
import static net.blogteamthreecoderhivebe.domain.member.entity.QMemberApply.memberApply;
import static net.blogteamthreecoderhivebe.domain.post.entity.QPost.post;
import static net.blogteamthreecoderhivebe.domain.post.entity.QPostJob.postJob;

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

    @Override
    public Page<Post> getAllPost(PostCategory postCategory, List<Long> regions, List<Long> jobs, PostStatus postStatus, Pageable pageable) {
        List<Post> Posts = queryFactory
                .select(post)
                .from(post)
                .join(post.postJobs, postJob)
                .join(post.location, location).fetchJoin()
                .where(
                        postCategoryEq(postCategory), regionIn(regions), jobsIn(jobs), postStatusEq(postStatus)
                )
                .groupBy(post.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.modifiedAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post);

        return PageableExecutionUtils.getPage(Posts, pageable, countQuery::fetchOne);
    }

    private BooleanExpression postCategoryEq(PostCategory postCategory) {
        return postCategory != null ? post.postCategory.eq(postCategory): null;
    }

    private BooleanExpression regionIn(List<Long> regions) {
        return regions != null ? post.location.id.in(regions): null;
    }

    private BooleanExpression jobsIn(List<Long> jobs) {
        return jobs != null ? postJob.job.id.in(jobs): null;
    }

    private BooleanExpression postStatusEq(PostStatus postStatus) {
        return postStatus != null ? post.postStatus.eq(postStatus): null;
    }


}


