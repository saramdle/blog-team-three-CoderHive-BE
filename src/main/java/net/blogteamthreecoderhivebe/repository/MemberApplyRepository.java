package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.MemberApply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberApplyRepository extends JpaRepository<MemberApply, Long> {
    List<MemberApply> findByMember_Id(Long memberId);
}
