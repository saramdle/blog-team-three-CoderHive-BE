package net.blogteamthreecoderhivebe.domain.post.repository;

import net.blogteamthreecoderhivebe.domain.post.entity.SkillRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRequirementRepository extends SkillRequirementCustom, JpaRepository<SkillRequirement, Long> {

}
