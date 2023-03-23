package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.Technology;
import net.blogteamthreecoderhivebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {

}
