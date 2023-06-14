package net.blogteamthreecoderhivebe.domain.member.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;

import java.util.Optional;

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

    private static BooleanExpression eqMember(Member member) {
        return applicationInfo.member.eq(member);
    }

    private static BooleanExpression eqRecruitJob(RecruitmentJob recruitmentJob) {
        return applicationInfo.recruitmentJob.eq(recruitmentJob);
    }
}
