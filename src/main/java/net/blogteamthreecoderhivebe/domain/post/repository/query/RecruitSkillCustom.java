package net.blogteamthreecoderhivebe.domain.post.repository.query;

import net.blogteamthreecoderhivebe.domain.info.entity.Skill;

import java.util.List;

public interface RecruitSkillCustom {
    List<Skill> findSkills(Long postId);
}
