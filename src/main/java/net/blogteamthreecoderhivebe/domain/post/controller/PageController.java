package net.blogteamthreecoderhivebe.domain.post.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.post.dto.response.PostWithApplyNumberResponse;
import net.blogteamthreecoderhivebe.domain.post.repository.querydsl.PostSearchCond;
import net.blogteamthreecoderhivebe.domain.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PageController {
    private final PostService postService;

    @GetMapping
    public Page<PostWithApplyNumberResponse> searchPosts(@RequestParam Long memberId,
                                                         @ModelAttribute PostSearchCond searchCond,
                                                         Pageable pageable) {
        return postService.searchPosts(memberId, searchCond, pageable);
    }
}
