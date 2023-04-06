package net.blogteamthreecoderhivebe.config;

import net.blogteamthreecoderhivebe.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService
    ) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/members/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        //.loginPage("/login")
                        //CommonOAuth2Provider.GOOGLE
                        .authorizationEndpoint(authorization -> authorization
                                .baseUri("/login/oauth2/authorization")  // localhost:8080/login/oauth2/authorization/google
                                //.baseUri("/")   // localhost:8080/google
                        )
                        .redirectionEndpoint(redirect -> redirect
                                .baseUri("/")
                        )
//                        .tokenEndpoint(token -> {
//                            var defaultMapConverter = new DefaultMapOAuth2AccessTokenResponseConverter();
//                            Converter<Map<String, Object>, OAuth2AccessTokenResponse> socialMapConverter = tokenResponse -> {
//                                var withTokenType = new HashMap<>(tokenResponse);
//                                withTokenType.put(OAuth2ParameterNames.TOKEN_TYPE, OAuth2AccessToken.TokenType.BEARER.getValue());
//                                return defaultMapConverter.convert(withTokenType);
//                            };
//
//                            var httpConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
//                            httpConverter.setAccessTokenResponseConverter(socialMapConverter);
//
//                            var restOperations = new RestTemplate(List.of(new FormHttpMessageConverter(), httpConverter));
//                            restOperations.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
//
//                            var client = new DefaultAuthorizationCodeTokenResponseClient();
//                            client.setRestOperations(restOperations);
//
//                            token.accessTokenResponseClient(client);
//                        })

                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oauth2UserService)
                        )

                        //.defaultSuccessUrl("/")
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                        .logoutSuccessUrl("/login/oauth2/mainpage")
                )
                ;

        return http.build();
    }

    @Bean
    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService(
            MemberService memberService

    ) {

        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        return (userRequest) -> {
            OAuth2User oAuth2User = delegate.loadUser(userRequest);

            return null;
        };
    }
}
