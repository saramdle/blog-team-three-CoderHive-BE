package net.blogteamthreecoderhivebe.domain.member.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitJob;

import java.util.List;
import java.util.Optional;

import static net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult.PASS;
import static net.blogteamthreecoderhivebe.domain.member.entity.QApplicationInfo.applicationInfo;

@RequiredArgsConstructor
public class ApplyInfoCustomImpl implements ApplyInfoCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ApplicationResult> findApplyResult(Member member, RecruitJob recruitJob) {
        ApplicationResult applicationResult = queryFactory.select(applicationInfo.applicationResult)
                .from(applicationInfo)
                .where(eqMember(member), eqRecruitJob(recruitJob))
                .fetchOne();
        return Optional.ofNullable(applicationResult);
    }

    /**
     * 해당 게시글에서 모집중인 직무들의 합격 회원(참여자) 목록 조회
     */
    @Override
    public List<Member> findPassMembers(Post post) {
        List<RecruitJob> recruitJobs = post.getRecruitJobs();

        return queryFactory.select(applicationInfo.member)
                .from(applicationInfo)
                .where(inRecruitJobs(recruitJobs), eqApplyResult(PASS))
                .fetch();
    }

    private static BooleanExpression eqApplyResult(ApplicationResult applyResult) {
        return applicationInfo.applicationResult.eq(applyResult);
    }

    private static BooleanExpression inRecruitJobs(List<RecruitJob> recruitJobs) {
        List<Long> recruitJobIds = recruitJobs.stream()
                .map(RecruitJob::getId)
                .toList();

        return applicationInfo.recruitJob.id.in(recruitJobIds);
    }

    private static BooleanExpression eqMember(Member member) {
        return applicationInfo.member.eq(member);
    }

    private static BooleanExpression eqRecruitJob(RecruitJob recruitJob) {
        return applicationInfo.recruitJob.eq(recruitJob);
    }
}
