package net.blogteamthreecoderhivebe.domain.post.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;
import net.blogteamthreecoderhivebe.domain.post.dto.PostWithApplyNumberDto;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.PostJob;
import net.blogteamthreecoderhivebe.domain.post.repository.PostRepository;
import net.blogteamthreecoderhivebe.domain.post.repository.SkillRequirementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {
    private final PostRepository postRepository;
    private final SkillRequirementRepository skillRequirementRepository;

    public Page<PostWithApplyNumberDto> searchPost(PostCategory postCategory, List<Long> regions, List<Long> jobs, PostStatus postStatus, Pageable pageable) {
        Page<Post> postInfos = postRepository.getAllPost(postCategory, regions, jobs, postStatus, pageable);
        List<PostWithApplyNumberDto> postWithApplyNumberDtos = postInfos.stream()
                .map(p -> {
                    int number = 0;
                    int passNumber = 0;
                    for (PostJob postJob : p.getPostJobs()) {
                        number += postJob.getNumber();
                        passNumber += postJob.getPassNumber();
                    }
                    List<String> postSkills = skillRequirementRepository.searchTechnology(p.getId()); // TODO: 성능 문제 발생 할 수 있기 때문에 query 를 한방에 주는 방식으로 refactor 해야함
                    return PostWithApplyNumberDto.from(p, postSkills, number, passNumber);
                }).toList();

        return new PageImpl<>(postWithApplyNumberDtos, postInfos.getPageable(), postInfos.getTotalElements());
    }
}
