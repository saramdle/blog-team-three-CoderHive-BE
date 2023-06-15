package net.blogteamthreecoderhivebe.domain.post.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.entity.Skill;

import java.util.List;

import static net.blogteamthreecoderhivebe.domain.post.entity.QRecruitmentSkill.recruitmentSkill;

@RequiredArgsConstructor
public class RecruitmentSkillCustomImpl implements RecruitmentSkillCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Skill> findSkills(Long postId) {
        return queryFactory
                .select(recruitmentSkill.skill)
                .from(recruitmentSkill)
                .where(eqPostId(postId))
                .fetch();
    }

    private static BooleanExpression eqPostId(Long postId) {
        return recruitmentSkill.post.id.eq(postId);
    }
}
