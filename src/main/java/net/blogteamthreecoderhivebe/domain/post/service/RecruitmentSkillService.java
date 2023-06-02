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
        skillService.findSkills(skillIds).stream()
                .map(RecruitmentSkill::from)
                .forEach(post::addRecruitSkill);
    }

    @Transactional(readOnly = true)
    public List<String> findRecruitSkillDetails(Long postId) {
        return recruitmentSkillRepository.findSkills(postId).stream()
                .map(Skill::getDetail)
                .toList();
    }
}
