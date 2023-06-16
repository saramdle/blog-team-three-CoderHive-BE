package net.blogteamthreecoderhivebe.domain.post.repository;

import net.blogteamthreecoderhivebe.domain.post.entity.RecruitSkill;
import net.blogteamthreecoderhivebe.domain.post.repository.querydsl.RecruitSkillCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitSkillRepository extends JpaRepository<RecruitSkill, Long>, RecruitSkillCustom {

}
