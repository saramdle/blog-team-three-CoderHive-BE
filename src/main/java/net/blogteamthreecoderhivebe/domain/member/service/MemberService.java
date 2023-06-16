package net.blogteamthreecoderhivebe.domain.member.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import net.blogteamthreecoderhivebe.domain.info.service.JobService;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplyResult;
import net.blogteamthreecoderhivebe.domain.member.dto.MemberDto;
import net.blogteamthreecoderhivebe.domain.member.dto.MemberWithPostDto;
import net.blogteamthreecoderhivebe.domain.member.dto.SignUpDto;
import net.blogteamthreecoderhivebe.domain.member.dto.response.SignUpResponse;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberRepository;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberSkillRepository;
import net.blogteamthreecoderhivebe.domain.post.dto.PostDto;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.repository.PostRepository;
import net.blogteamthreecoderhivebe.domain.post.service.RecruitSkillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private static final String NOT_FOUND_MEMBER = "EMAIL[%s] 유저를 찾을 수 없습니다.";
    private static final String NOT_MATCH_MEMBER_GUEST = "EMAIL[%s] 유저의 Role이 Guest가 아닙니다.";

    private final JobService jobService;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final MemberSkillRepository memberSkillRepository;
    private final RecruitSkillService recruitSkillService;

    private Member searchMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("멤버가 없습니다 - memberId: " + memberId));
    }

    public Member searchMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("멤버가 없습니다 - email:" + email));
    }

    public MemberWithPostDto searchMemberInfoAll(Long memberId) {
        Member member = searchMember(memberId);

        List<String> skills = memberSkillRepository.searchSkill(memberId);

        MemberDto memberDto = MemberDto.from(member, skills);

        List<PostDto> memberHostPostDtos = postRepository.findAllByMember_Id(memberId).stream()
                .map(post -> {
                    List<String> postSkills = recruitSkillService.findRecruitSkillDetails(post.getId());
                    return PostDto.from(post, postSkills);
                })
                .toList();
        Map<ApplyResult, List<Post>> applyPostsMap = postRepository.memberApplyPost(memberId);

        List<PostDto> appliedPostDtos = applyPostsMap.get(ApplyResult.APPLY).stream()
                .map(p -> {
                    List<String> postSkills = recruitSkillService.findRecruitSkillDetails(p.getId());
                    return PostDto.from(p, postSkills);
                })
                .toList();

        List<PostDto> participatedDtos = applyPostsMap.get(ApplyResult.PASS).stream()
                .map(p -> {
                    List<String> postSkills = recruitSkillService.findRecruitSkillDetails(p.getId());
                    return PostDto.from(p, postSkills);
                })
                .toList();

        return MemberWithPostDto.from(memberDto, memberHostPostDtos, appliedPostDtos, participatedDtos);
    }

    /**
     * 회원 가입
     */
    @Transactional
    public SignUpResponse signUp(SignUpDto signUpDto) {
        Member member = searchMember(signUpDto.email());
        if (member.isNotGuest()) {
            throw new EntityNotFoundException(String.format(NOT_MATCH_MEMBER_GUEST, signUpDto.email()));
        }
        Job job = jobService.findOne(signUpDto.jobId());

        member.update(signUpDto.nickname(), signUpDto.memberLevel(), signUpDto.memberCareer(), job);
        return SignUpResponse.from(member);
    }

    /**
     * 신규 회원인지 확인
     */
    public boolean isNewMember(String email) {
        return memberRepository.findByEmail(email).isEmpty();
    }

    /**
     * 사용자의 ROLE 확인
     */
    public SignUpResponse validMember(String email) {
        return SignUpResponse.from(searchMember(email));
    }

}
