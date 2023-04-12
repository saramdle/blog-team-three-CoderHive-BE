package net.blogteamthreecoderhivebe.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.dto.MemberWithPostDto;
import net.blogteamthreecoderhivebe.dto.SignUpDto;
import net.blogteamthreecoderhivebe.dto.request.SignUpRequest;
import net.blogteamthreecoderhivebe.dto.response.MemberInfoWithPostResponse;
import net.blogteamthreecoderhivebe.dto.response.MyInfoWithPostResponse;
import net.blogteamthreecoderhivebe.dto.security.MemberPrincipal;
import net.blogteamthreecoderhivebe.service.LikePostService;
import net.blogteamthreecoderhivebe.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final LikePostService likePostService;

    @GetMapping("/my")
    public MyInfoWithPostResponse searchMyInfo(@RequestParam Long memberId) {
        MemberWithPostDto memberWithPostDto = memberService.searchMemberInfoAll(memberId);
        List<Long> postIds = likePostService.searchLikePost(memberId);
        return MyInfoWithPostResponse.from(memberWithPostDto, postIds);
    }

    @GetMapping
    public MemberInfoWithPostResponse searchMemberInfo(@RequestParam Long memberId, @RequestParam Long searchMemberId) {
        MemberWithPostDto memberWithPostDto = memberService.searchMemberInfoAll(searchMemberId);
        List<Long> postIds = likePostService.searchLikePost(memberId);
        return MemberInfoWithPostResponse.from(memberWithPostDto, postIds);
    }

    @PostMapping("/my")
    public void signUpMember(@RequestBody SignUpRequest signUpRequest,
                                     @AuthenticationPrincipal MemberPrincipal memberPrincipal)
    throws UserPrincipalNotFoundException {
        if (memberPrincipal != null && signUpRequest.email().equals(memberPrincipal.email())) {
            SignUpDto signUpDto = SignUpDto.from(signUpRequest);
            memberService.saveMember(signUpDto);
        } else {
            throw new IllegalArgumentException("회원정보가 일치하지 않습니다.");
        }
//        SignUpDto signUpDto = SignUpDto.from(signUpRequest);
//        memberService.saveMember(signUpDto);
    }
}
