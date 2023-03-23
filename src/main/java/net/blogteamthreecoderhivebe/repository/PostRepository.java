package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.Post;
import net.blogteamthreecoderhivebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
