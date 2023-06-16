package net.blogteamthreecoderhivebe.domain.member.repository;

import net.blogteamthreecoderhivebe.domain.member.entity.ApplyInfo;
import net.blogteamthreecoderhivebe.domain.member.repository.querydsl.ApplyInfoCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyInfoRepository extends JpaRepository<ApplyInfo, Long>, ApplyInfoCustom {
    List<ApplyInfo> findByMember_Id(Long memberId);
}
