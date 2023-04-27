package net.blogteamthreecoderhivebe.domain.member.repository.querydsl;

import java.util.List;

public interface MemberSkillCustom {
    List<String> searchSkill(Long memberId);
}
