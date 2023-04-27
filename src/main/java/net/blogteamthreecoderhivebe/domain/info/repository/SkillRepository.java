package net.blogteamthreecoderhivebe.domain.info.repository;

import net.blogteamthreecoderhivebe.domain.info.dto.SkillDto;
import net.blogteamthreecoderhivebe.domain.info.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<SkillDto> findTop4ByDetailContaining(String keyword);
}
