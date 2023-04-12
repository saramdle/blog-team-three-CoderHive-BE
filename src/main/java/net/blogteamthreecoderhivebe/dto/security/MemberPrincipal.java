package net.blogteamthreecoderhivebe.dto.security;

import lombok.Builder;
import lombok.Getter;
import net.blogteamthreecoderhivebe.dto.MemberDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record MemberPrincipal(
                              String email,
                              Boolean isSignUp,
                              Collection<? extends GrantedAuthority> authorities,
                              Map<String, Object> oAuth2Attributes
) implements UserDetails, OAuth2User {

    public static MemberPrincipal of(String email, Boolean isSignUp) {
        return MemberPrincipal.of(email, isSignUp, Collections.emptyMap());
    }

    public static MemberPrincipal of(String email, Boolean isSignUp, Map<String, Object> oAuth2Attributes) {
        // 지금은 인증만 하고 권한을 다루고 있지 않아서 임의로 세팅한다.
        Set<RoleType> roleTypes = Set.of(RoleType.USER);

        return new MemberPrincipal(
                email,
                isSignUp,
                roleTypes.stream()
                        .map(RoleType::getDescription)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet())
                ,
                oAuth2Attributes
        );
    }

    public static MemberPrincipal from(String email, Boolean isSignUp) {
        return MemberPrincipal.of(email, isSignUp);
    }

    public static MemberPrincipal from(MemberDto dto) {
        return MemberPrincipal.of(
                dto.email(),
                true
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {    return authorities;    }
    @Override
    public String getPassword() {   return null;    }
    @Override
    public boolean isAccountNonExpired() {  return true;   }
    @Override
    public boolean isAccountNonLocked() {   return true;   }
    @Override
    public boolean isCredentialsNonExpired() {  return true;   }
    @Override
    public boolean isEnabled() {    return true;   }
    @Override
    public String getName() {   return email;    }


    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2Attributes;
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Getter
    public enum RoleType {
        ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

        private final String description;

        RoleType(String description) {
            this.description = description;
        }

    }
}
