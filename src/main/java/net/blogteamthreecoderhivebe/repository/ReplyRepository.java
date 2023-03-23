package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.Reply;
import net.blogteamthreecoderhivebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
