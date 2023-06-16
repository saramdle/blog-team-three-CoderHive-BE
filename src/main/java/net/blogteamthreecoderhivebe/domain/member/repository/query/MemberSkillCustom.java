package net.blogteamthreecoderhivebe.domain.member.repository.query;

import java.util.List;

public interface MemberSkillCustom {
    List<String> searchSkill(Long memberId);
}
