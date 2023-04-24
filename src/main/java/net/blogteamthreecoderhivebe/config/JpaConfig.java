package net.blogteamthreecoderhivebe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@Configuration
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(UUID.randomUUID().toString());

        // 추후 Spring Security의 Authentication을 가져와서 사용자명을 반환하도록 변경 예정
        // return () -> Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
