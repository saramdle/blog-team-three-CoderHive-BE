package net.blogteamthreecoderhivebe.domain.member.repository;

import net.blogteamthreecoderhivebe.domain.member.entity.ApplyInfo;
import net.blogteamthreecoderhivebe.domain.member.repository.query.ApplyInfoCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyInfoRepository extends ApplyInfoCustom, JpaRepository<ApplyInfo, Long> {
    List<ApplyInfo> findByMember_Id(Long memberId);
}
