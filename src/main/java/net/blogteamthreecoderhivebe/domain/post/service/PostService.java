package net.blogteamthreecoderhivebe.domain.post.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.service.JobService;
import net.blogteamthreecoderhivebe.domain.info.service.LocationService;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.service.ApplicationInfoService;
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

import static net.blogteamthreecoderhivebe.domain.member.dto.response.MemberResponseDto.MemberInfoOnPostDetail;
import static net.blogteamthreecoderhivebe.domain.post.dto.response.RecruitJobResponseDto.RecruitInfoOnPostDetail;

@RequiredArgsConstructor
@Service
public class PostService {

    public static final String NOT_FOUND_POST = "ID[%s] 해당 게시글을 찾을 수 없습니다.";

    private final PostRepository postRepository;

    private final JobService jobService;
    private final MemberService memberService;
    private final LocationService locationService;
    private final RecruitmentJobService recruitmentJobService;
    private final ApplicationInfoService applicationInfoService;
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

    /**
     * 게시글 상세 조회
     */
    @Transactional(readOnly = true)
    public PostResponseDto.Detail findPost(Long postId, String email) {
        Member loginMember = memberService.searchMember(email); // 현재 로그인된 회원
        Post post = findOne(postId);

        // 모집 정보
        List<RecruitmentJob> recruitmentJobs = post.getRecruitmentJobs();
        List<RecruitInfoOnPostDetail> recruitInfoOnPostDetails = recruitmentJobs.stream()
                .map(recruitmentJob -> {
                            ApplicationResult result = applicationInfoService.getApplyResult(loginMember, recruitmentJob);
                            return RecruitInfoOnPostDetail.from(recruitmentJob, result);
                        }
                ).toList();

        // 작성자 -> leader
        MemberInfoOnPostDetail leader = MemberInfoOnPostDetail.from(post.getMember());
        // 참가자 -> participants
        List<MemberInfoOnPostDetail> participants = applicationInfoService.findPassMembers(post).stream()
                .map(MemberInfoOnPostDetail::from)
                .toList();

        return PostResponseDto.Detail.from(post, recruitInfoOnPostDetails, leader, participants);
    }

    private Post findOne(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(String.format(NOT_FOUND_POST, postId)));
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
