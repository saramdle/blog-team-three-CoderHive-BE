package net.blogteamthreecoderhivebe.global.config;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.global.auth.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SpringSecurityConfig {
    private final CustomOAuth2UserService oauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers("/", "/members/**").permitAll()
                    .anyRequest().permitAll()//authenticated()
                .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        //.logoutSuccessUrl("/login/oauth2/mainpage")
                        //.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                        .userService(oauth2UserService);

        return http.build();
    }
}
