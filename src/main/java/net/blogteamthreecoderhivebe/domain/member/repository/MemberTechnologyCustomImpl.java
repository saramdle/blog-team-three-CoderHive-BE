package net.blogteamthreecoderhivebe.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static net.blogteamthreecoderhivebe.domain.info.entity.QTechnology.technology;
import static net.blogteamthreecoderhivebe.domain.member.entity.QMemberTechnology.memberTechnology;

@RequiredArgsConstructor
public class MemberTechnologyCustomImpl implements MemberTechnologyCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> searchTechnology(Long memberId) {
        return queryFactory
                .select(technology.detail)
                .from(memberTechnology)
                .join(memberTechnology.technology, technology)
                .where(memberTechnology.member.id.eq(memberId))
                .fetch();
    }
}
