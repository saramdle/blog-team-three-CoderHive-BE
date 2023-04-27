package net.blogteamthreecoderhivebe.domain.post.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.heart.service.HeartService;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;
import net.blogteamthreecoderhivebe.domain.post.dto.response.PostWithApplyNumberResponse;
import net.blogteamthreecoderhivebe.domain.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PageController {
    private final PostService postService;
    private final HeartService heartService;

    @GetMapping
    public Page<PostWithApplyNumberResponse> searchPostList(
            @RequestParam Long memberId,
            @RequestParam(required = false) PostCategory postCategory,
            @RequestParam(required = false) List<Long> regions,
            @RequestParam(required = false) List<Long> jobs,
            @RequestParam(required = false) PostStatus postStatus,
            Pageable pageable
    ) {
        List<Long> likePosts = heartService.searchHeartPostIds(memberId);
        return postService.searchPost(postCategory, regions, jobs, postStatus, pageable).map(
                p -> PostWithApplyNumberResponse.from(p, likePosts)
        );
    }
}
