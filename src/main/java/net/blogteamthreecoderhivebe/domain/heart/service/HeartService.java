package net.blogteamthreecoderhivebe.domain.heart.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.heart.repository.HeartRepository;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HeartService {
    private final HeartRepository heartRepository;

    @Transactional(readOnly = true)
    public List<Long> searchHeartPostIds(Long memberId) {
        return heartRepository.findHeartPosts(memberId).stream()
                .map(Post::getId)
                .toList();
    }
}
