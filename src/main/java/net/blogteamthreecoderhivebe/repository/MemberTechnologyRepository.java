package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.MemberTechnology;
import net.blogteamthreecoderhivebe.repository.querydsl.MemberTechnologyCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTechnologyRepository extends MemberTechnologyCustom, JpaRepository<MemberTechnology, Long> {
}
