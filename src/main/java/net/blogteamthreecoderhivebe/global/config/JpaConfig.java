package net.blogteamthreecoderhivebe.global.config;

import net.blogteamthreecoderhivebe.global.auth.dto.MemberPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (ObjectUtils.isEmpty(authentication)) {
                return Optional.empty();
            } else {
                MemberPrincipal principal = (MemberPrincipal) authentication.getPrincipal();
                return Optional.of(principal.getEmail());
            }
        };
    }
}
