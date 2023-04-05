package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.SkillRequirement;
import net.blogteamthreecoderhivebe.repository.querydsl.SkillRequirementCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRequirementRepository extends SkillRequirementCustom, JpaRepository<SkillRequirement, Long> {

}
