package net.blogteamthreecoderhivebe.domain.member.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import net.blogteamthreecoderhivebe.domain.info.repository.JobRepository;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberCareer;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberLevel;
import net.blogteamthreecoderhivebe.domain.member.dto.MemberDto;
import net.blogteamthreecoderhivebe.domain.member.dto.MemberWithPostDto;
import net.blogteamthreecoderhivebe.domain.member.dto.request.SignUpRequest;
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

import static net.blogteamthreecoderhivebe.domain.member.constant.MemberRole.USER;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final JobRepository jobRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final MemberSkillRepository memberSkillRepository;
    private final RecruitmentSkillRepository recruitmentSkillRepository;

    private Member searchMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("멤버가 없습니다 - memberId: " + memberId));
    }

    private Member searchMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("멤버가 없습니다 - email:" + email));
    }

    public MemberWithPostDto searchMemberInfoAll(Long memberId) {
        Member member = searchMember(memberId);

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
     * 회원 가입
     */
    @Transactional
    public Long save(SignUpRequest signUpRequest, String email) {
        Job job = jobRepository.findById(signUpRequest.jobId()).orElseThrow();
        MemberCareer memberCareer = MemberCareer.find(signUpRequest.career());
        MemberLevel memberLevel = MemberLevel.find(signUpRequest.level());

        Member member = Member.builder()
                .email(email)
                .nickname(signUpRequest.nickname())
                .job(job)
                .career(memberCareer)
                .level(memberLevel)
                .memberRole(USER)
                .build();

        return memberRepository.save(member).getId();
    }

    /**
     * 신규 회원인지 확인
     */
    public boolean isNewMember(String email) {
        return memberRepository.findByEmail(email).isEmpty();
    }
}
