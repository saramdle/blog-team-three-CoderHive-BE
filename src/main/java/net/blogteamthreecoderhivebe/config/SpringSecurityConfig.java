package net.blogteamthreecoderhivebe.config;

import jakarta.persistence.EntityNotFoundException;
import net.blogteamthreecoderhivebe.dto.security.*;
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
                )
                ;

        return http.build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService(
            MemberService memberService
    ) {

        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        return (userRequest) -> {
            OAuth2User oAuth2User = delegate.loadUser(userRequest);
            //System.out.println("oauthUser : " + oAuth2User);
            String registrationId = userRequest.getClientRegistration().getRegistrationId();

            //KakaoOAuth2Response kakaoResponse = KakaoOAuth2Response.from(oAuth2User.getAttributes());
            SocialLoginDto socialLoginDto = null;  // SocialLoginDto.fromKakao(kakaoResponse);
            //System.out.println(oAuth2User.getAttributes());

            if (registrationId.toUpperCase().equals("KAKAO")) {
                KakaoOAuth2Response kakaoResponse = KakaoOAuth2Response.from(oAuth2User.getAttributes());
                socialLoginDto = SocialLoginDto.fromKakao(kakaoResponse);
            }
            if (registrationId.toUpperCase().equals("NAVER")) {
                NaverOAuth2Response naverResponse = NaverOAuth2Response.from(oAuth2User.getAttributes());
                socialLoginDto = SocialLoginDto.fromNaver(naverResponse);
            }
            if (registrationId.toUpperCase().equals("GOOGLE")) {
                GoogleOauth2Response googleResponse = GoogleOauth2Response.from(oAuth2User.getAttributes());
                socialLoginDto = SocialLoginDto.fromGoogle(googleResponse);
            }
            System.out.println(socialLoginDto);
            String email = socialLoginDto.email();

            if (socialLoginDto.email().isEmpty()) throw new EntityNotFoundException("멤버가 없습니다 - member email: " + email);
            else {
                return memberService.searchMemberByEmail(email)
                        .map(MemberPrincipal::from)
                        .orElseGet(() ->
                                MemberPrincipal.from(memberService.saveMember(email)
                                )
                        );
            }
        };
    }
}
