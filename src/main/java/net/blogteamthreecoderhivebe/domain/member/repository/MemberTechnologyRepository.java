package net.blogteamthreecoderhivebe.domain.member.repository;

import net.blogteamthreecoderhivebe.domain.member.entity.MemberTechnology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTechnologyRepository extends MemberTechnologyCustom, JpaRepository<MemberTechnology, Long> {
}
