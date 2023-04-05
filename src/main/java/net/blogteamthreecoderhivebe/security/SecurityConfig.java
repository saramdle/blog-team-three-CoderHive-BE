package net.blogteamthreecoderhivebe.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

/*    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests(request -> request
                //.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()    // 컨트롤러에서 화면 파일명 리턴(JSP, 타임리프)
                .anyRequest().authenticated()                                  // 어떠한 요청이라도 인증 필요
        );
        http.oauth2Login((oauth2Login) -> oauth2Login
                .userInfoEndpoint()
        );



        return http.build();
    }*/
}
