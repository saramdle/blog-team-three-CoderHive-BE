package net.blogteamthreecoderhivebe.domain.comment.repository;

import net.blogteamthreecoderhivebe.domain.comment.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
