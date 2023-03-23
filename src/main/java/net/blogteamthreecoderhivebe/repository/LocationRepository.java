package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.LikePost;
import net.blogteamthreecoderhivebe.entity.Location;
import net.blogteamthreecoderhivebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
