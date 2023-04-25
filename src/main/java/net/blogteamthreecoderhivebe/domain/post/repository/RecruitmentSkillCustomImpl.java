package net.blogteamthreecoderhivebe.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static net.blogteamthreecoderhivebe.domain.info.entity.QSkill.skill;
import static net.blogteamthreecoderhivebe.domain.post.entity.QRecruitmentSkill.recruitmentSkill;

@RequiredArgsConstructor
public class RecruitmentSkillCustomImpl implements RecruitmentSkillCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> searchSkill(Long postId) {
        return queryFactory
                .select(skill.detail)
                .from(recruitmentSkill)
                .join(recruitmentSkill.skill, skill)
                .where(recruitmentSkill.post.id.eq(postId))
                .fetch();
    }
}
