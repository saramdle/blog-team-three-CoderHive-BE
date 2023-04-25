package net.blogteamthreecoderhivebe.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static net.blogteamthreecoderhivebe.domain.info.entity.QSkill.skill;
import static net.blogteamthreecoderhivebe.domain.post.entity.QSkillRequirement.skillRequirement;

@RequiredArgsConstructor
public class SkillRequirementCustomImpl implements SkillRequirementCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> searchTechnology(Long postId) {
        return queryFactory
                .select(skill.detail)
                .from(skillRequirement)
                .join(skillRequirement.skill, skill)
                .where(skillRequirement.post.id.eq(postId))
                .fetch();
    }
}
