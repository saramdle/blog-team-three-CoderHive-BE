package net.blogteamthreecoderhivebe.domain.post.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.service.JobService;
import net.blogteamthreecoderhivebe.domain.info.service.LocationService;
import net.blogteamthreecoderhivebe.domain.member.service.MemberService;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.dto.PostWithApplyNumberDto;
import net.blogteamthreecoderhivebe.domain.post.dto.request.PostRequestDto;
import net.blogteamthreecoderhivebe.domain.post.dto.response.PostResponseDto;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;
import net.blogteamthreecoderhivebe.domain.post.repository.PostRepository;
import net.blogteamthreecoderhivebe.domain.post.repository.RecruitmentSkillRepository;
import net.blogteamthreecoderhivebe.domain.post.repository.querydsl.PostSearchCond;
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
    private final JobService jobService;
    private final MemberService memberService;
    private final PostRepository postRepository;
    private final LocationService locationService;
    private final RecruitmentJobService recruitmentJobService;
    private final RecruitmentSkillService recruitmentSkillService;
    private final RecruitmentSkillRepository recruitmentSkillRepository;

    /**
     * 게시글 등록
     */
    @Transactional
    public PostResponseDto.Save save(PostRequestDto.Save dto, String memberEmail) {
        Post post = postRepository.save(makePost(dto, memberEmail));

        recruitmentSkillService.save(dto.skillIds(), post);
        recruitmentJobService.save(dto.recruitmentJobs(), post);

        return new PostResponseDto.Save(post.getId());
    }

    public Page<PostWithApplyNumberDto> searchPost(PostCategory postCategory,
                                                   List<Long> regions,
                                                   List<Long> jobs,
                                                   PostStatus postStatus,
                                                   Pageable pageable) {
        Page<Post> postInfos = postRepository.getAllPost(searchCond, pageable);
        List<PostWithApplyNumberDto> postWithApplyNumberDtos = postInfos.stream()
                .map(p -> {
                    int number = 0;
                    int passNumber = 0;
                    for (RecruitmentJob postJob : p.getRecruitmentJobs()) {
                        number += postJob.getNumber();
                        passNumber += postJob.getPassNumber();
                    }
                    List<String> skills = recruitmentSkillRepository.searchSkill(p.getId()); // TODO: 성능 문제 발생 할 수 있기 때문에 query 를 한방에 주는 방식으로 refactor 해야함
                    return PostWithApplyNumberDto.from(p, skills, number, passNumber);
                }).toList();

        return new PageImpl<>(postWithApplyNumberDtos, postInfos.getPageable(), postInfos.getTotalElements());
    }

    private Post makePost(PostRequestDto.Save dto, String memberEmail) {
        return Post.builder()
                .member(memberService.searchMember(memberEmail))
                .job(jobService.findOne(dto.myJobId()))
                .location(locationService.findOne(dto.locationId()))
                .postCategory(PostCategory.find(dto.category()))
                .title(dto.title())
                .content(dto.content())
                .thumbImageUrl(dto.thumbImageUrl())
                .platforms(dto.platforms())
                .build();
    }
}
