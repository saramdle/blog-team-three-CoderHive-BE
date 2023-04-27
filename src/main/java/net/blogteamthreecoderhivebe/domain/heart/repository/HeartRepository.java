package net.blogteamthreecoderhivebe.domain.heart.repository;

import net.blogteamthreecoderhivebe.domain.heart.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    List<Heart> findHeartByMember_Id(Long memberId);
}
