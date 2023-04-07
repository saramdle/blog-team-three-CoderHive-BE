package net.blogteamthreecoderhivebe.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static net.blogteamthreecoderhivebe.entity.QSkillRequirement.skillRequirement;
import static net.blogteamthreecoderhivebe.entity.QTechnology.technology;

@RequiredArgsConstructor
public class SkillRequirementCustomImpl implements SkillRequirementCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> searchTechnology(Long postId) {
        return queryFactory
                .select(technology.detail)
                .from(skillRequirement)
                .join(skillRequirement.technology, technology)
                .where(skillRequirement.post.id.eq(postId))
                .fetch();
    }
}
