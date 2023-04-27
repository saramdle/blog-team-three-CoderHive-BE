package net.blogteamthreecoderhivebe.domain.comment.repository;

import net.blogteamthreecoderhivebe.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
