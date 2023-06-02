package net.blogteamthreecoderhivebe.domain.post.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.entity.Skill;
import net.blogteamthreecoderhivebe.domain.info.service.SkillService;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentSkill;
import net.blogteamthreecoderhivebe.domain.post.repository.RecruitmentSkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class RecruitmentSkillService {
    private final SkillService skillService;
    private final RecruitmentSkillRepository recruitmentSkillRepository;

    public void save(List<Long> skillIds, Post post) {
        List<Skill> skills = skillService.findSkills(skillIds);
        skills.stream()
                .map(skill -> makeRecruitmentSkill(skill, post))
                .forEach(recruitmentSkillRepository::save);
    }

    private static RecruitmentSkill makeRecruitmentSkill(Skill skill, Post post) {
        return RecruitmentSkill.of(skill, post);
    }

    @Transactional(readOnly = true)
    public List<String> findRecruitSkillDetails(Long postId) {
        return recruitmentSkillRepository.findSkills(postId).stream()
                .map(Skill::getDetail)
                .toList();
    }
}
