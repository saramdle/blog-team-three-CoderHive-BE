package net.blogteamthreecoderhivebe.domain.post.repository.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.entity.Skill;

import java.util.List;

import static net.blogteamthreecoderhivebe.domain.post.entity.QRecruitSkill.recruitSkill;

@RequiredArgsConstructor
public class RecruitSkillCustomImpl implements RecruitSkillCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Skill> findSkills(Long postId) {
        return queryFactory
                .select(recruitSkill.skill)
                .from(recruitSkill)
                .where(eqPostId(postId))
                .fetch();
    }

    private static BooleanExpression eqPostId(Long postId) {
        return recruitSkill.post.id.eq(postId);
    }
}
