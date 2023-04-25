package net.blogteamthreecoderhivebe.domain.post.repository;

import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentSkillRepository extends RecruitmentSkillCustom, JpaRepository<RecruitmentSkill, Long> {

}
