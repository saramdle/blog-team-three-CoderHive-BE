package net.blogteamthreecoderhivebe.domain.post.repository.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
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
import static net.blogteamthreecoderhivebe.domain.member.entity.QApplicationInfo.applicationInfo;
import static net.blogteamthreecoderhivebe.domain.post.entity.QPost.post;
import static net.blogteamthreecoderhivebe.domain.post.entity.QRecruitmentJob.recruitmentJob;

@RequiredArgsConstructor
public class PostCustomImpl implements PostCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Map<ApplicationResult, List<Post>> memberApplyPost(Long memberId) {
        List<Tuple> result = queryFactory
                .select(post, applicationInfo)
                .from(applicationInfo)
                .join(applicationInfo.recruitmentJob, recruitmentJob)
                .join(recruitmentJob.post, post)
                .where(
                        applicationInfo.member.id.eq(memberId)
                )
                .fetch();

        Map<ApplicationResult, List<Post>> posts = new HashMap<>();

        List<Post> passPost = new ArrayList<>();
        List<Post> nonPost = new ArrayList<>();
        for (Tuple tuple : result) {
            System.out.println(tuple.get(applicationInfo.applicationResult));
            if (tuple.get(applicationInfo.applicationResult) == ApplicationResult.NON) {
                nonPost.add(tuple.get(post));
            } else if (tuple.get(applicationInfo.applicationResult) == ApplicationResult.PASS) {
                passPost.add(tuple.get(post));
            }
        }
        posts.put(ApplicationResult.NON, nonPost);
        posts.put(ApplicationResult.PASS, passPost);
        return posts;
    }

    @Override
    public Page<Post> getAllPost(PostSearchCond searchCond, Pageable pageable) {
        PostCategory category = searchCond.postCategory();
        List<Long> locations = searchCond.locationIds();
        List<Long> jobs = searchCond.jobIds();
        PostStatus status = searchCond.postStatus();

        List<Post> Posts = queryFactory
                .select(post)
                .from(post)
                .join(post.recruitmentJobs, recruitmentJob)
                .join(post.location, location).fetchJoin()
                .where(
                        postCategoryEq(category), regionIn(locations), jobsIn(jobs), postStatusEq(status)
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
        return jobs != null ? recruitmentJob.job.id.in(jobs) : null;
    }

    private BooleanExpression postStatusEq(PostStatus postStatus) {
        return postStatus != null ? post.postStatus.eq(postStatus): null;
    }
}


