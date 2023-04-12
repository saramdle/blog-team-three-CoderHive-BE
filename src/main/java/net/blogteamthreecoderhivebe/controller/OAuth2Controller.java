package net.blogteamthreecoderhivebe.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.dto.MemberDto;
import net.blogteamthreecoderhivebe.dto.security.MemberPrincipal;
import net.blogteamthreecoderhivebe.service.MemberService;
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


//    @GetMapping("/")
//    public RedirectView oauth2LoginUser(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {
//        //System.out.println("member : " + memberPrincipal);
//        String memberEmail = memberPrincipal.email();
//        Optional<MemberDto> memberDto = memberService.searchMemberByEmail(memberEmail);
//
//        if (memberDto.get().nickname() == null) return new RedirectView("http://localhost:3000/register");
//        else {
//            return new RedirectView("http://localhost:3000");
//        }
//    }

    @GetMapping("/")
    public RedirectView oauth2LoginUser(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        //System.out.println("member : " + memberPrincipal);
        String memberEmail = memberPrincipal.email();
        Optional<MemberDto> memberDto = memberService.searchMemberByEmail(memberEmail);

        if (memberPrincipal.isSignUp()) return new RedirectView("http://localhost:3000");
        else {
            return new RedirectView("http://localhost:3000/register");  // 추가 정보 입력 페이지
        }
    }



//    @GetMapping("/login/oauth2/code/{socialLoginType}")
//    public RedirectView redirectUri(
//            @PathVariable("socialLoginType") String socialLoginType,
//            @RequestParam("code") String code) {
//        System.out.println("redirect : " + socialLoginType + " -> " + code);  // code : Access Token
//        return new RedirectView("http://localhost:3000");
//    }


}
