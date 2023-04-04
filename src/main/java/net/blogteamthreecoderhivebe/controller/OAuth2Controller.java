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

//    @GetMapping("/code/google")
//    public RedirectView redirectUri() {
    @GetMapping("/code/{registrationId}")
    public RedirectView redirectUri(@PathVariable("registrationId") String registrationId) {
        System.out.println("redirect : " + registrationId);
        return new RedirectView("http://localhost:3000");
    }

}
