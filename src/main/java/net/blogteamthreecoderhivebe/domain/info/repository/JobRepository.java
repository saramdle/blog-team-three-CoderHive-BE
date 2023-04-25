package net.blogteamthreecoderhivebe.domain.info.repository;

import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
