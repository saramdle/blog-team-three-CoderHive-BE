package net.blogteamthreecoderhivebe.domain.post.repository;

import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentSkill;
import net.blogteamthreecoderhivebe.domain.post.repository.querydsl.RecruitmentSkillCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentSkillRepository extends RecruitmentSkillCustom, JpaRepository<RecruitmentSkill, Long> {

}
