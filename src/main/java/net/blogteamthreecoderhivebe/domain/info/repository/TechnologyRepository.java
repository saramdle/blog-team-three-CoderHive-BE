package net.blogteamthreecoderhivebe.domain.info.repository;

import net.blogteamthreecoderhivebe.domain.info.dto.TechnologyDto;
import net.blogteamthreecoderhivebe.domain.info.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    List<TechnologyDto> findTop4ByDetailContaining(String keyword);
}
