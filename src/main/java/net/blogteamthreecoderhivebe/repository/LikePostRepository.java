package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.LikePost;
import net.blogteamthreecoderhivebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {

}
