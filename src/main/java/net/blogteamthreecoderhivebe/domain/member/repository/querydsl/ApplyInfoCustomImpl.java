package net.blogteamthreecoderhivebe.domain.member.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;

import java.util.List;
import java.util.Optional;

import static net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult.PASS;
import static net.blogteamthreecoderhivebe.domain.member.entity.QApplicationInfo.applicationInfo;

@RequiredArgsConstructor
public class ApplyInfoCustomImpl implements ApplyInfoCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ApplicationResult> findApplyResult(Member member, RecruitmentJob recruitmentJob) {
        ApplicationResult applicationResult = queryFactory.select(applicationInfo.applicationResult)
                .from(applicationInfo)
                .where(eqMember(member), eqRecruitJob(recruitmentJob))
                .fetchOne();
        return Optional.ofNullable(applicationResult);
    }

    /**
     * 해당 게시글에서 모집중인 직무들의 합격 회원(참여자) 목록 조회
     */
    @Override
    public List<Member> findPassMembers(Post post) {
        List<RecruitmentJob> recruitmentJobs = post.getRecruitmentJobs();

        return queryFactory.select(applicationInfo.member)
                .from(applicationInfo)
                .where(inRecruitJobs(recruitmentJobs), eqApplyResult(PASS))
                .fetch();
    }

    private static BooleanExpression eqApplyResult(ApplicationResult applyResult) {
        return applicationInfo.applicationResult.eq(applyResult);
    }

    private static BooleanExpression inRecruitJobs(List<RecruitmentJob> recruitJobs) {
        List<Long> recruitJobIds = recruitJobs.stream()
                .map(RecruitmentJob::getId)
                .toList();

        return applicationInfo.recruitmentJob.id.in(recruitJobIds);
    }

    private static BooleanExpression eqMember(Member member) {
        return applicationInfo.member.eq(member);
    }

    private static BooleanExpression eqRecruitJob(RecruitmentJob recruitmentJob) {
        return applicationInfo.recruitmentJob.eq(recruitmentJob);
    }
}
