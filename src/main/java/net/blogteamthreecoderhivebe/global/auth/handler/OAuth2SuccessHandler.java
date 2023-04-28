package net.blogteamthreecoderhivebe.global.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.blogteamthreecoderhivebe.domain.member.service.MemberService;
import net.blogteamthreecoderhivebe.global.auth.dto.MemberPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final MemberService memberService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String email = ((MemberPrincipal) authentication.getPrincipal()).getEmail();

        log.info("oauth login success : {} {}",email, authentication.getAuthorities());

        String redirectUrl;
        if (isFirstLogin(email)) {
            // 신규 회원일 경우 회원 가입 페이지로 이동
            redirectUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/register")
                    .queryParam("email", email)
                    .build().toUriString();
        } else {
            // 기존 회원일 경우 홈로 이동
            redirectUrl = "http://localhost:3000";
        }

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

    private boolean isFirstLogin(String email) {
        return memberService.isNewMember(email);
    }
}
