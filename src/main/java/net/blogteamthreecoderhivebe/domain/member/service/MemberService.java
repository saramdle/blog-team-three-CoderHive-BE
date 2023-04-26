package net.blogteamthreecoderhivebe.domain.member.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberCareer;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberLevel;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberRole;
import net.blogteamthreecoderhivebe.domain.member.dto.MemberDto;
import net.blogteamthreecoderhivebe.domain.member.dto.MemberWithPostDto;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberRepository;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberSkillRepository;
import net.blogteamthreecoderhivebe.domain.post.dto.PostDto;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.repository.PostRepository;
import net.blogteamthreecoderhivebe.domain.post.repository.RecruitmentSkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final MemberSkillRepository memberSkillRepository;
    private final RecruitmentSkillRepository recruitmentSkillRepository;

    public MemberDto searchMember(Long memberId) {
        return memberRepository.findById(memberId)
                .map(MemberDto::from)
                .orElseThrow(() -> new EntityNotFoundException("멤버가 없습니다 - memberId: " + memberId));
    }

    public MemberWithPostDto searchMemberInfoAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("멤버가 없습니다 - memberId: " + memberId));

        List<String> skills = memberSkillRepository.searchSkill(memberId);

        MemberDto memberDto = MemberDto.from(member, skills);

        List<PostDto> memberHostPostDtos = postRepository.findAllByMember_Id(memberId).stream()
                .map(post -> {
                    List<String> postSkills = recruitmentSkillRepository.searchSkill(post.getId());
                    return PostDto.from(post, postSkills);
                })
                .toList();
        Map<ApplicationResult, List<Post>> applyPostsMap = postRepository.memberApplyPost(memberId);

        List<PostDto> appliedPostDtos = applyPostsMap.get(ApplicationResult.NON).stream()
                .map(p -> {
                    List<String> postSkills = recruitmentSkillRepository.searchSkill(p.getId());
                    return PostDto.from(p, postSkills);
                })
                .toList();

        List<PostDto> participatedDtos = applyPostsMap.get(ApplicationResult.PASS).stream()
                .map(p -> {
                    List<String> postSkills = recruitmentSkillRepository.searchSkill(p.getId());
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
     * 사용자 등록
     */
    public MemberDto saveMember(String nickname,
                                String email,
                                MemberLevel level,
                                MemberCareer career,
                                MemberRole memberRole,
                                String profileImageUrl,
                                String introduction,
                                Job job) {
        return MemberDto.from(memberRepository.save(
                Member.builder()
                        .nickname(nickname)
                        .email(email)
                        .level(level)
                        .career(career)
                        .memberRole(memberRole)
                        .profileImageUrl(profileImageUrl)
                        .introduction(introduction)
                        .job(job)
                        .build())
        );
    }

    /**
     * 이메일로 사용자 찾기
     */
    public Optional<MemberDto> searchMemberByEmail(String email) {
        return memberRepository.findByEmail(email).map(MemberDto::from);
    }

    @Transactional
    public MemberDto saveMember(String email) {
        return MemberDto.from(memberRepository.save(
                Member.builder()
                        .email(email)
                        .build())
        );
    }

    /**
     * 신규 회원인지 확인
     */
    public Boolean isNewMember(String email) {
        return memberRepository.findByEmail(email).isEmpty();
    }
}
