package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.entity.PostJob;
import net.blogteamthreecoderhivebe.repository.querydsl.PostJobRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJobRepository extends PostJobRepositoryCustom, JpaRepository<PostJob, Long> {

}
