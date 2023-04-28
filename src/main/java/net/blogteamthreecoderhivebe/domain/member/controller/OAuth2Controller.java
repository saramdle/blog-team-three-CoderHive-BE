package net.blogteamthreecoderhivebe.domain.member.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.service.MemberService;
import net.blogteamthreecoderhivebe.global.auth.dto.MemberPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@RestController
public class OAuth2Controller {
    private final MemberService memberService;

    @GetMapping
    public RedirectView oAuth2Login(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        String email = memberPrincipal.getEmail();

        if (memberService.isNewMember(email)) { // 신규 회원일 경우 회원가입 페이지로 이동
            return new RedirectView("http://localhost:3000/register");
        } else { // 기존 회원일 경우 홈으로 이동
            return new RedirectView("http://localhost:3000");
        }
    }

/*
    @GetMapping("/login/oauth2/code/{socialLoginType}")
    public RedirectView redirectUri(
            @PathVariable("socialLoginType") String socialLoginType,
            @RequestParam("code") String code) {
        System.out.println("redirect : " + socialLoginType + " -> " + code);  // code : Access Token
        return new RedirectView("http://localhost:3000");
    }

    @GetMapping("/login/oauth2/mainpage")
    public RedirectView redirectHomepage() {
        return new RedirectView("http://localhost:3000");
    }


    @GetMapping("/")
    public RedirectView redirectUri() {
        return new RedirectView("http://localhost:3000");
    }


    @GetMapping("/logout")
    public RedirectView logoutUri() {
        return new RedirectView("http://localhost:3000");
    }
*/
}
