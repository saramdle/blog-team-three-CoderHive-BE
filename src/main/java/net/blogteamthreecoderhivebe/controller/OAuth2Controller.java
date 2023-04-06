package net.blogteamthreecoderhivebe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@RequestMapping("/login/oauth2")
@RestController
public class OAuth2Controller {


    @PostMapping("")
    public void oauth2LoginUser() {
        System.out.println("google");
    }

    @GetMapping("/code/{socialLoginType}")
    public RedirectView redirectUri(
//    public String re(
            @PathVariable("socialLoginType") String socialLoginType,
            @RequestParam("code") String code) {
        //GoogleOauth
        System.out.println("redirect : " + socialLoginType + " -> " + code);  // code : Access Token
        return new RedirectView("http://localhost:3000");
    }

    @GetMapping("/mainpage")
    public RedirectView redirectHomepage() {
        return new RedirectView("http://localhost:3000");
    }

/*
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
