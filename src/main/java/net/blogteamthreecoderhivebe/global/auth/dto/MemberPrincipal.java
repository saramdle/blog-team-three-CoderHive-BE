package net.blogteamthreecoderhivebe.global.auth.dto;

import lombok.Getter;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collections;

@Getter
public class MemberPrincipal extends DefaultOAuth2User {
    private String email;
    private MemberRole memberRole;

    public MemberPrincipal(SocialLoginDto socialLoginDto, MemberRole memberRole) {
        super(
                Collections.singleton(new SimpleGrantedAuthority(memberRole.getDescription())),
                socialLoginDto.attributes(),
                socialLoginDto.nameAttributeKey()
        );
        this.email = socialLoginDto.email();
        this.memberRole = memberRole;
    }

    public boolean isGuest() {
        return memberRole.isGuest();
    }
}
