package net.blogteamthreecoderhivebe.domain.info.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.entity.Skill;
import net.blogteamthreecoderhivebe.domain.info.repository.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SkillService {
    private static final String NOT_FOUND_SKILL = "ID[%s] 기술을 찾을 수 없습니다.";

    private final SkillRepository skillRepository;

    public Skill findOne(Long skillId) {
        return skillRepository.findById(skillId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_SKILL, skillId)));
    }
}
