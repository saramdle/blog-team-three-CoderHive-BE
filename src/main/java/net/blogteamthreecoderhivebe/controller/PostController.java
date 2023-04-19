package net.blogteamthreecoderhivebe.controller;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.dto.PostWithPostJobsDto;
import net.blogteamthreecoderhivebe.dto.response.PostWithApplyNumberResponse;
import net.blogteamthreecoderhivebe.entity.constant.PostCategory;
import net.blogteamthreecoderhivebe.entity.constant.PostStatus;
import net.blogteamthreecoderhivebe.service.LikePostService;
import net.blogteamthreecoderhivebe.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {
    private final PostService postService;
    private final LikePostService likePostService;

    @GetMapping
    public Page<PostWithApplyNumberResponse> searchPostList(
            @RequestParam Long memberId,
            @RequestParam(required = false) PostCategory postCategory,
            @RequestParam(required = false) List<Long> regions,
            @RequestParam(required = false) List<Long> jobs,
            @RequestParam(required = false) PostStatus postStatus,
            Pageable pageable
    ) {
        List<Long> likePosts = likePostService.searchLikePost(memberId);
        return postService.searchPost(postCategory, regions, jobs, postStatus, pageable).map(
                p -> PostWithApplyNumberResponse.from(p, likePosts)
        );
    }


    @GetMapping("/{postId}")
    public PostWithPostJobsDto searchDetailPost(@PathVariable Long postId) {
        return postService.searchDetailPost(postId);
    }
}
