package net.blogteamthreecoderhivebe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/members/**").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        //.loginPage("/login/oauth2/google")
                        //CommonOAuth2Provider.GOOGLE
                        .authorizationEndpoint(authorization -> authorization
                                .baseUri("/login/oauth2/authorization")  // localhost:8080/login/oauth2/authorization/google
                                //.baseUri("/")   // localhost:8080/google
                        )
                        .redirectionEndpoint(redirect -> redirect
                                .baseUri("/")
                        )

                        /*.userInfoEndpoint(userInfo -> userInfo
                                .userService(this.oauth2UserService())
                        )*/

                        //.defaultSuccessUrl("/")
                );

        return http.build();
    }

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {

        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        return (userRequest) -> {
            OAuth2User oAuth2User = delegate.loadUser(userRequest);

            return null;
        };
    }
}
