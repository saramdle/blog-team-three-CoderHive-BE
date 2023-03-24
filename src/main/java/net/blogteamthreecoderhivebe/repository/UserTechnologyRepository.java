package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.User;
import net.blogteamthreecoderhivebe.entity.UserTechnology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTechnologyRepository extends JpaRepository<UserTechnology, Long> {

}
