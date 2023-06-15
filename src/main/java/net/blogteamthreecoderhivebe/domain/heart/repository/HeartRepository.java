package net.blogteamthreecoderhivebe.domain.heart.repository;

import net.blogteamthreecoderhivebe.domain.heart.entity.Heart;
import net.blogteamthreecoderhivebe.domain.heart.repository.query.HeartCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long>, HeartCustom {
}
