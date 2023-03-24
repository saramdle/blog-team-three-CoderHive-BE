package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.Job;
import net.blogteamthreecoderhivebe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
