package net.blogteamthreecoderhivebe.domain.post.repository;

import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends PostRepositoryCustom, JpaRepository<Post, Long> {
   List<Post> findAllByMember_Id(Long memberId);
}
