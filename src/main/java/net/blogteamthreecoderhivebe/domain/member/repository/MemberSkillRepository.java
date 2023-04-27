package net.blogteamthreecoderhivebe.domain.member.repository;

import net.blogteamthreecoderhivebe.domain.member.entity.MemberSkill;
import net.blogteamthreecoderhivebe.domain.member.repository.querydsl.MemberSkillCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSkillRepository extends MemberSkillCustom, JpaRepository<MemberSkill, Long> {

}
