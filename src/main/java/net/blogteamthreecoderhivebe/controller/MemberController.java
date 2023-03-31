package net.blogteamthreecoderhivebe.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.dto.MemberWithPostDto;
import net.blogteamthreecoderhivebe.dto.response.MemberInfoWithPostResponse;
import net.blogteamthreecoderhivebe.dto.response.MyInfoWithPostResponse;
import net.blogteamthreecoderhivebe.service.LikePostService;
import net.blogteamthreecoderhivebe.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
