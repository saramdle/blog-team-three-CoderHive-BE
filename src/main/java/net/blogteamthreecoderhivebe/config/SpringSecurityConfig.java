package net.blogteamthreecoderhivebe.config;

import jakarta.persistence.EntityNotFoundException;
import net.blogteamthreecoderhivebe.dto.security.KakaoOAuth2Response;
import net.blogteamthreecoderhivebe.dto.security.MemberPrincipal;
import net.blogteamthreecoderhivebe.dto.security.SocialLoginDto;
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
            //SocialLoginDto socialLoginDto = null;
            KakaoOAuth2Response kakaoResponse = KakaoOAuth2Response.from(oAuth2User.getAttributes());
            final SocialLoginDto socialLoginDto = SocialLoginDto.fromKakao(kakaoResponse);

            if (registrationId.toUpperCase().equals("KAKAO")) {
                //KakaoOAuth2Response kakaoResponse = KakaoOAuth2Response.from(oAuth2User.getAttributes());
                //socialLoginDto = SocialLoginDto.fromKakao(kakaoResponse);


            }
            if (registrationId.toUpperCase().equals("NAVER")) {

            }
            if (registrationId.toUpperCase().equals("GOOGLE")) {

            }

            //String providerId = String.valueOf(kakaoResponse.id());
            //String memberId = registrationId + "_" + providerId;

            String nickname = socialLoginDto.nickname();
            String email = socialLoginDto.email();

            if (socialLoginDto.email().isEmpty()) throw new EntityNotFoundException("멤버가 없습니다 - member email: " + email);
            else {
                return memberService.searchMemberByEmail(email)
                        .map(MemberPrincipal::from)
                        .orElseGet(() ->
                                MemberPrincipal.from(memberService.saveMember(nickname, email)
                                )
                        );

            }


        };


    }
}
