package net.blogteamthreecoderhivebe.domain.member.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.heart.service.HeartService;
import net.blogteamthreecoderhivebe.domain.member.dto.MemberWithPostDto;
import net.blogteamthreecoderhivebe.domain.member.dto.request.SignUpRequest;
import net.blogteamthreecoderhivebe.domain.member.dto.response.MemberInfoWithPostResponse;
import net.blogteamthreecoderhivebe.domain.member.dto.response.MyInfoWithPostResponse;
import net.blogteamthreecoderhivebe.domain.member.service.MemberService;
import net.blogteamthreecoderhivebe.global.auth.dto.MemberPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {
    private final MemberService memberService;
    private final HeartService heartService;

    @PostMapping
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest,
                                         @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        Long memberId = memberService.save(signUpRequest, memberPrincipal.getEmail());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(memberId)
                .toUri();

        return ResponseEntity.created(location).body("회원 가입 성공");
    }

    @GetMapping("/my")
    public MyInfoWithPostResponse searchMyInfo(@RequestParam Long memberId) {
        MemberWithPostDto memberWithPostDto = memberService.searchMemberInfoAll(memberId);
        List<Long> postIds = heartService.searchHeartPostIds(memberId);
        return MyInfoWithPostResponse.from(memberWithPostDto, postIds);
    }

    @GetMapping
    public MemberInfoWithPostResponse searchMemberInfo(@RequestParam Long memberId, @RequestParam Long searchMemberId) {
        MemberWithPostDto memberWithPostDto = memberService.searchMemberInfoAll(searchMemberId);
        List<Long> postIds = heartService.searchHeartPostIds(memberId);
        return MemberInfoWithPostResponse.from(memberWithPostDto, postIds);
    }
}
