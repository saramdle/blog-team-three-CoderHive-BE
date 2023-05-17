package net.blogteamthreecoderhivebe.domain.post.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.heart.service.HeartService;
import net.blogteamthreecoderhivebe.domain.post.dto.response.PostWithApplyNumberResponse;
import net.blogteamthreecoderhivebe.domain.post.repository.querydsl.PostSearchCond;
import net.blogteamthreecoderhivebe.domain.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PageController {
    private final PostService postService;
    private final HeartService heartService;

    @GetMapping
    public Page<PostWithApplyNumberResponse> searchPosts(@RequestParam Long memberId,
                                                         @ModelAttribute PostSearchCond searchCond,
                                                         Pageable pageable) {
        List<Long> likePosts = heartService.searchHeartPostIds(memberId);
        return postService.searchPost(searchCond, pageable).map(
                p -> PostWithApplyNumberResponse.from(p, likePosts)
        );
    }
}
