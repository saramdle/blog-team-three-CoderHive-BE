package net.blogteamthreecoderhivebe.global.auth.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.service.MemberService;
import net.blogteamthreecoderhivebe.global.auth.dto.MemberPrincipal;
import net.blogteamthreecoderhivebe.global.auth.dto.SocialLoginDto;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberService memberService;

    @Override
    public MemberPrincipal loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        //System.out.println("oAuth2User : " + oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // google, naver, kakao
        Map<String, Object> attributes = oAuth2User.getAttributes();
        //System.out.println("registrationId : " + registrationId);
        //System.out.println("attributes : " + attributes);

        SocialLoginDto socialLoginDto = SocialLoginDto.of(registrationId, attributes);
        //System.out.println("socialLoginDto : " + socialLoginDto);

        String email = socialLoginDto.email();

        if (email.isEmpty())
            throw new EntityNotFoundException("멤버가 없습니다 - email:" + email);
        else {
            return memberService.searchMemberByEmail(email)
                    .map(MemberPrincipal::from)
                    .orElseGet(() ->
                            MemberPrincipal.from(memberService.saveMember(email))
                    );
        }
    }
}
