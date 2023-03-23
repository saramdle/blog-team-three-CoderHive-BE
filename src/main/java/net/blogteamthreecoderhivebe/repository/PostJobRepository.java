package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.PostJob;
import net.blogteamthreecoderhivebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJobRepository extends JpaRepository<PostJob, Long> {

}
