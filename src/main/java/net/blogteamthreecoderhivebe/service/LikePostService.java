package net.blogteamthreecoderhivebe.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.repository.LikePostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class LikePostService {
    private final LikePostRepository likePostRepository;

    public List<Long> searchLikePost(Long memberId){
        return likePostRepository.findLikePostByMember_Id(memberId).stream()
                .map(p -> p.getPost().getId())
                .collect(Collectors.toList());
    }
}
