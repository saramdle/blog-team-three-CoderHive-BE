package net.blogteamthreecoderhivebe.domain.member.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.dto.MemberDto;
import net.blogteamthreecoderhivebe.domain.member.service.MemberService;
import net.blogteamthreecoderhivebe.global.auth.dto.MemberPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("")
@RestController
public class OAuth2Controller {

    final MemberService memberService;

    @GetMapping("/")
    public RedirectView oauth2LoginUser(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        //System.out.println("member : " + memberPrincipal);
        String memberEmail = memberPrincipal.email();
        Optional<MemberDto> memberDto = memberService.searchMemberByEmail(memberEmail);

        if (memberDto.get().nickname() == null) return new RedirectView("http://localhost:3000/register");
        else {
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
