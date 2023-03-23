package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.User;
import net.blogteamthreecoderhivebe.entity.UserApply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserApplyRepository extends JpaRepository<UserApply, Long> {

}
