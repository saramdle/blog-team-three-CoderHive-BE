package net.blogteamthreecoderhivebe.domain.post.repository.querydsl;

import net.blogteamthreecoderhivebe.domain.info.entity.Skill;

import java.util.List;

public interface RecruitSkillCustom {
    List<Skill> findSkills(Long postId);
}
