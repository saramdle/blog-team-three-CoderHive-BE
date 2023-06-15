package net.blogteamthreecoderhivebe.domain.post.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.heart.service.HeartService;
import net.blogteamthreecoderhivebe.domain.info.service.JobService;
import net.blogteamthreecoderhivebe.domain.info.service.LocationService;
import net.blogteamthreecoderhivebe.domain.member.service.MemberService;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.dto.request.PostRequestDto;
import net.blogteamthreecoderhivebe.domain.post.dto.response.PostResponseDto;
import net.blogteamthreecoderhivebe.domain.post.dto.response.PostSearchResponse;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;
import net.blogteamthreecoderhivebe.domain.post.repository.PostRepository;
import net.blogteamthreecoderhivebe.domain.post.service.vo.RecruitJobResult;
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
    public Page<PostSearchResponse> searchPosts(Long memberId, PostRequestDto.SearchCond searchCond, Pageable pageable) {
        Page<Post> posts = postRepository.findPosts(
                searchCond.postCategory(), searchCond.postStatus(), searchCond.locations(), searchCond.jobs(), pageable
        );

        List<PostSearchResponse> postSearchResponses = posts.stream()
                .map(post -> {
                            boolean isMemberHeartPost = isMemberHeartPost(post, memberId);
                            RecruitJobResult recruitJobResult = makeRecruitResult(post);
                            return PostSearchResponse.from(post, isMemberHeartPost, recruitJobResult);
                        }
                ).toList();

        return new PageImpl<>(postSearchResponses, posts.getPageable(), posts.getTotalElements());
    }

    private boolean isMemberHeartPost(Post post, Long memberId) {
        return post.getHeartMemberIds().contains(memberId);
    }

    private RecruitJobResult makeRecruitResult(Post post) {
        int totalNumber = 0;
        int totalPassNumber = 0;
        for (RecruitmentJob recruitmentJob : post.getRecruitmentJobs()) {
            totalNumber += recruitmentJob.getNumber();
            totalNumber += recruitmentJob.getPassNumber();
        }
        return new RecruitJobResult(totalNumber, totalPassNumber);
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
