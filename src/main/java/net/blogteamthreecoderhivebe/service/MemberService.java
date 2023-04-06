package net.blogteamthreecoderhivebe.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.dto.MemberDto;
import net.blogteamthreecoderhivebe.dto.MemberWithPostDto;
import net.blogteamthreecoderhivebe.dto.PostDto;
import net.blogteamthreecoderhivebe.entity.Job;
import net.blogteamthreecoderhivebe.entity.Member;
import net.blogteamthreecoderhivebe.entity.Post;
import net.blogteamthreecoderhivebe.entity.constant.ApplyResult;
import net.blogteamthreecoderhivebe.entity.constant.MemberCareer;
import net.blogteamthreecoderhivebe.entity.constant.MemberLevel;
import net.blogteamthreecoderhivebe.entity.constant.MemberRole;
import net.blogteamthreecoderhivebe.repository.MemberRepository;
import net.blogteamthreecoderhivebe.repository.MemberTechnologyRepository;
import net.blogteamthreecoderhivebe.repository.PostRepository;
import net.blogteamthreecoderhivebe.repository.SkillRequirementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberTechnologyRepository memberTechnologyRepository;
    private final PostRepository postRepository;
    private final SkillRequirementRepository skillRequirementRepository;

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
    public MemberDto saveMember(String nickname,
                                String email,
                                MemberLevel level,
                                MemberCareer career,
                                MemberRole memberRole,
                                String profileImageUrl,
                                String introduction,
                                Job job
                                ) {
        return MemberDto.from(memberRepository.save(Member.builder()
                                        .nickname(nickname)
                                        .email(email)
                                        .level(level)
                                        .career(career)
                                        .memberRole(memberRole)
                                        .profileImageUrl(profileImageUrl)
                                        .introduction(introduction)
                                        .job(job)
                                        .createdBy(nickname)
                                        .modifiedBy(nickname)
                                        .build())
        );
    }


}
