package net.blogteamthreecoderhivebe.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static net.blogteamthreecoderhivebe.domain.info.entity.QSkill.skill;
import static net.blogteamthreecoderhivebe.domain.member.entity.QMemberSkill.memberSkill;

@RequiredArgsConstructor
public class MemberSkillCustomImpl implements MemberSkillCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> searchSkill(Long memberId) {
        return queryFactory
                .select(skill.detail)
                .from(memberSkill)
                .join(memberSkill.skill, skill)
                .where(memberSkill.member.id.eq(memberId))
                .fetch();
    }
}
