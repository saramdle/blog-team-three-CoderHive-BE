package net.blogteamthreecoderhivebe.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;

import java.util.List;

import static net.blogteamthreecoderhivebe.entity.QMemberTechnology.memberTechnology;
import static net.blogteamthreecoderhivebe.entity.QTechnology.technology;

@AllArgsConstructor
public class MemberTechnologyCustomImpl implements MemberTechnologyCustom{

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
