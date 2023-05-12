package net.blogteamthreecoderhivebe.domain.member.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.heart.service.HeartService;
import net.blogteamthreecoderhivebe.domain.member.dto.MemberWithPostDto;
import net.blogteamthreecoderhivebe.domain.member.dto.SignUpDto;
import net.blogteamthreecoderhivebe.domain.member.dto.request.SignUpRequest;
import net.blogteamthreecoderhivebe.domain.member.dto.response.MemberInfoWithPostResponse;
import net.blogteamthreecoderhivebe.domain.member.dto.response.MyInfoWithPostResponse;
import net.blogteamthreecoderhivebe.domain.member.dto.response.SignUpResponse;
import net.blogteamthreecoderhivebe.domain.member.service.MemberService;
import net.blogteamthreecoderhivebe.global.auth.dto.MemberPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {
    private final MemberService memberService;
    private final HeartService heartService;

    /**
     * 회원 가입 - 추가 정보 등록
     * need   > SignUpRequest, MemberPrincipal
     * return > SignInResponse
     * TODO 1) 사용자 정보 유효성 검사 / email, nickname, jobId, career, level
     */
    @PostMapping
    //public SingUpResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest,
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest,
                                 @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
//        Long memberId = memberService.signUp(SignUpDto.of(signUpRequest, memberPrincipal.getEmail()));
        SignUpResponse signUpResponse = memberService.signUp(SignUpDto.of(signUpRequest, memberPrincipal.getEmail()));

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(memberId)
//                .toUri();
        return signUpResponse;
        //return ResponseEntity.created(location).body("회원 가입 성공");
    }

    /**
     * 유효한 회원인지 확인 기능 - ROLE_USER
     * need   > email, MemberPrincipal
     * return > SignUpResponse
     * TODO 1) email과 principal-email 검증
     */
    @GetMapping("/{email}/valid")
    public SignUpResponse validationMember(@PathVariable String email,
                                           @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        // principal 검증
        if (memberPrincipal == null) {
            // TODO 1) 로그인 안되어 있음
        }
        // email과 principal-email 검증
        if (!email.equals(memberPrincipal.getEmail())) {
            // TODO 2) "유효성 검사할 이메일"과 "로그인한 이메일" 같지 않음
        }
        return memberService.validMember(email);
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
