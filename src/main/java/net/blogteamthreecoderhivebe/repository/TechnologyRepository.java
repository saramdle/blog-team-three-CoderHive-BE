package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.dto.TechnologyDto;
import net.blogteamthreecoderhivebe.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    List<TechnologyDto> findTop4ByDetailContaining(String keyword);
}
