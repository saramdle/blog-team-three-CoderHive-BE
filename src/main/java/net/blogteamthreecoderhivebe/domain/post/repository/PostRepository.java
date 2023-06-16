package net.blogteamthreecoderhivebe.domain.post.repository;

import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.repository.query.PostCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends PostCustom, JpaRepository<Post, Long> {
   List<Post> findAllByMember_Id(Long memberId);
}
