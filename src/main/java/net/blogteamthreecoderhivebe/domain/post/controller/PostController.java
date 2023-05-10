package net.blogteamthreecoderhivebe.domain.post.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.post.service.PostService;
import net.blogteamthreecoderhivebe.global.auth.dto.MemberPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static net.blogteamthreecoderhivebe.domain.post.dto.request.PostRequestDto.SaveRequest;
import static net.blogteamthreecoderhivebe.domain.post.dto.response.PostResponseDto.SaveResponse;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {
    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SaveResponse register(@RequestBody SaveRequest request,
                                 @AuthenticationPrincipal MemberPrincipal principal) {
        return postService.save(request, principal.getEmail());
    }
}
