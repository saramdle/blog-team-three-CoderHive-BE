package net.blogteamthreecoderhivebe.domain.member.repository;

import net.blogteamthreecoderhivebe.domain.member.entity.ApplicationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationInfoRepository extends JpaRepository<ApplicationInfo, Long> {
    List<ApplicationInfo> findByMember_Id(Long memberId);
}
