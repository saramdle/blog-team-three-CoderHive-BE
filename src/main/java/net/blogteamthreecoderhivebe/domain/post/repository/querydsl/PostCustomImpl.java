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
    public Page<Post> findPosts(PostCategory category, PostStatus status, List<Long> locations, List<Long> jobs, Pageable pageable) {
        List<Post> posts = queryFactory
                .select(post)
                .from(post)
                .join(post.recruitmentJobs, recruitmentJob)
                .join(post.location, location).fetchJoin()
                .where(
                        eqPostCategory(category), inLocations(locations), inJobs(jobs), eqPostStatus(status)
                )
                .groupBy(post.id)
                .offset(pageable.getOffset()) // 어디서부터 보여줄 건지
                .limit(pageable.getPageSize()) // 한 페이지에 보여줄 개수
                .orderBy(post.modifiedAt.desc()) // 가장 최근 글이 맨 앞에 오도록 정렬
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post);

        return PageableExecutionUtils.getPage(posts, pageable, countQuery::fetchOne);
    }

    /**
     * 카테고리 검색 조건
     */
    private BooleanExpression eqPostCategory(PostCategory postCategory) {
        return postCategory != null ? post.postCategory.eq(postCategory): null;
    }

    /**
     * 모집상태 검색 조건
     */
    private BooleanExpression eqPostStatus(PostStatus postStatus) {
        return postStatus != null ? post.postStatus.eq(postStatus) : null;
    }

    /**
     * 지역 검색 조건
     */
    private BooleanExpression inLocations(List<Long> locations) {
        return locations != null ? post.location.id.in(locations) : null;
    }

    /**
     * 직무 검색 조건
     */
    private BooleanExpression inJobs(List<Long> jobs) {
        return jobs != null ? recruitmentJob.job.id.in(jobs) : null;
    }
}


