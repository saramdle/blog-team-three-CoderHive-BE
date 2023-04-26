package net.blogteamthreecoderhivebe.global.auth.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Override
    public MemberPrincipal loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        //System.out.println("oAuth2User : " + oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // google, naver, kakao
        String nameAttributeKey = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        //System.out.println("registrationId : " + registrationId);
        //System.out.println("attributes : " + attributes);

        SocialLoginDto socialLoginDto = SocialLoginDto.of(registrationId, nameAttributeKey, attributes);
        //System.out.println("socialLoginDto : " + socialLoginDto);

        Member member = save(socialLoginDto);
        return new MemberPrincipal(socialLoginDto);
    }

    private Member save(SocialLoginDto socialLoginDto) {
        Member member = memberRepository.findByEmail(socialLoginDto.email())
                .orElse(socialLoginDto.toEntity());
        return memberRepository.save(member);
    }
}
