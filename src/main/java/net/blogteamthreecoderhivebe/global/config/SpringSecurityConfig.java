package net.blogteamthreecoderhivebe.global.config;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.global.auth.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class SpringSecurityConfig {
    private final CustomOAuth2UserService oauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/members/**").permitAll()
                        .anyRequest().permitAll()//authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        //.authorizationEndpoint(authorization  -> authorization
                        //        .baseUri("/"))
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oauth2UserService)
                        )
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                        .logoutSuccessUrl("/login/oauth2/mainpage")
                );

        return http.build();
    }
}
