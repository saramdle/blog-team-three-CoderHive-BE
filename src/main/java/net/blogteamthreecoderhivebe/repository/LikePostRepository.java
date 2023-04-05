package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    List<LikePost> findLikePostByMember_Id(Long memberId);
}
