package net.blogteamthreecoderhivebe.domain.post.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.heart.service.HeartService;
import net.blogteamthreecoderhivebe.domain.info.service.JobService;
import net.blogteamthreecoderhivebe.domain.info.service.LocationService;
import net.blogteamthreecoderhivebe.domain.member.service.MemberService;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.dto.PostWithApplyNumberDto;
import net.blogteamthreecoderhivebe.domain.post.dto.request.PostRequestDto;
import net.blogteamthreecoderhivebe.domain.post.dto.response.PostResponseDto;
import net.blogteamthreecoderhivebe.domain.post.dto.response.PostWithApplyNumberResponse;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;
import net.blogteamthreecoderhivebe.domain.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final JobService jobService;
    private final HeartService heartService;
    private final MemberService memberService;
    private final PostRepository postRepository;
    private final LocationService locationService;
    private final RecruitmentJobService recruitmentJobService;
    private final RecruitmentSkillService recruitmentSkillService;

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

    /**
     * 게시글 전체 조회
     */
    @Transactional(readOnly = true)
    public Page<PostWithApplyNumberResponse> searchPosts(Long memberId, PostRequestDto.SearchCond searchCond, Pageable pageable) {
        Page<Post> postInfos = postRepository.findPosts(
                searchCond.postCategory(), searchCond.postStatus(), searchCond.locations(), searchCond.jobs(), pageable
        );

        List<PostWithApplyNumberDto> postWithApplyNumberDtos = postInfos.stream()
                .map(p -> {
                    int number = 0;
                    int passNumber = 0;
                    for (RecruitmentJob postJob : p.getRecruitmentJobs()) {
                        number += postJob.getNumber();
                        passNumber += postJob.getPassNumber();
                    }
                    List<String> skills = recruitmentSkillService.findRecruitSkillDetails(p.getId()); // TODO: 성능 문제 발생 할 수 있기 때문에 query 를 한방에 주는 방식으로 refactor 해야함
                    return PostWithApplyNumberDto.from(p, skills, number, passNumber);
                }).toList();

        // 회원이 좋아요 누른 게시글 목록 조회
        List<Long> heartPostIds = heartService.searchHeartPostIds(memberId);

        return new PageImpl<>(postWithApplyNumberDtos, postInfos.getPageable(), postInfos.getTotalElements())
                .map(postWithApplyNumberDto -> PostWithApplyNumberResponse.from(postWithApplyNumberDto, heartPostIds));
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
