package net.blogteamthreecoderhivebe.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.dto.*;
import net.blogteamthreecoderhivebe.entity.Member;
import net.blogteamthreecoderhivebe.entity.Post;
import net.blogteamthreecoderhivebe.entity.constant.ApplyResult;
import net.blogteamthreecoderhivebe.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberTechnologyRepository memberTechnologyRepository;
    private final PostRepository postRepository;
    private final SkillRequirementRepository skillRequirementRepository;

    private final JobRepository jobRepository;

    public MemberDto searchMember(Long memberId) {
        return memberRepository.findById(memberId).map(MemberDto::from)
                .orElseThrow(() -> new EntityNotFoundException("멤버가 없습니다 - memberId: " + memberId));
    }

    public MemberWithPostDto searchMemberInfoAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("멤버가 없습니다 - memberId: " + memberId));

        List<String> skills = memberTechnologyRepository.searchTechnology(memberId);

        MemberDto memberDto = MemberDto.from(member, skills);

        List<PostDto> memberHostPostDtos = postRepository.findAllByMember_Id(memberId).stream()
                .map(post -> {
                    List<String> postSkills = skillRequirementRepository.searchTechnology(post.getId());
                    return PostDto.from(post, postSkills);
                })
                .toList();
        Map<ApplyResult, List<Post>> applyPostsMap = postRepository.memberApplyPost(memberId);

        List<PostDto> appliedPostDtos = applyPostsMap.get(ApplyResult.NON).stream()
                .map(p -> {
                    List<String> postSkills = skillRequirementRepository.searchTechnology(p.getId());
                    return PostDto.from(p, postSkills);
                })
                .toList();

        List<PostDto> participatedDtos = applyPostsMap.get(ApplyResult.PASS).stream()
                .map(p -> {
                    List<String> postSkills = skillRequirementRepository.searchTechnology(p.getId());
                    return PostDto.from(p, postSkills);
                })
                .toList();

        return MemberWithPostDto.from(memberDto, memberHostPostDtos, appliedPostDtos, participatedDtos);
    }

    /**
     * 소셜 로그인을 이용한 사용자 회원가입
     * - Google
     * - Kakao
     * - Naver
     */

    /**
     * 이메일로 사용자 찾기
     *
     */
    public Optional<MemberDto> searchMemberByEmail(String email) {
        return memberRepository.findByEmail(email).map(MemberDto::from);
    }

    /**
     * 사용자 등록
     *
     */
    @Transactional
    public MemberDto saveMember(SignUpDto signUpDto) {
        JobDto jobDto = JobDto.from(jobRepository.findById(signUpDto.jobId()).get());
        return MemberDto.from(memberRepository.save(
                    Member.builder()
                            .email(signUpDto.email())
                            .nickname(signUpDto.nickname())
                            .job(jobDto.toEntity())
                            .level(signUpDto.level())
                            .career(signUpDto.career())
                            .createdBy(signUpDto.email())
                            .modifiedBy(signUpDto.email())
                            .build()
                )
        );
    }
}
