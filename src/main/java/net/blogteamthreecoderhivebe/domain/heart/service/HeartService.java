package net.blogteamthreecoderhivebe.domain.heart.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.heart.repository.HeartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class HeartService {
    private final HeartRepository heartRepository;

    public List<Long> searchHeartPostIds(Long memberId) {
        return heartRepository.findHeartByMember_Id(memberId).stream()
                .map(p -> p.getPost().getId())
                .toList();
    }
}
