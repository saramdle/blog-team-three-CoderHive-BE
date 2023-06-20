package net.blogteamthreecoderhivebe.domain.member.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplyResult;
import net.blogteamthreecoderhivebe.domain.member.entity.ApplyInfo;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.repository.ApplyInfoRepository;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitJob;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApplyInfoService {

    private final ApplyInfoRepository applyInfoRepository;

    /**
     * 회원의 지원 정보 조회
     */
    public List<ApplyInfo> searchApplyPost(Long memberId) {
        return applyInfoRepository.findByMember_Id(memberId);
    }

    /**
     * 회원이 모집직무에 지원한 결과 조회
     */
    public ApplyResult getApplyResult(Member member, RecruitJob recruitJob) {
        Optional<ApplyResult> applyResult = applyInfoRepository.findApplyResult(member, recruitJob);
        // 지원 이력이 없을 경우 미신청
        return applyResult.orElse(ApplyResult.NONE);
    }

    /**
     * 합격 회원(참여자) 조회
     */
    public List<Member> findPassMembers(Post post) {
        return applyInfoRepository.findPassMembers(post);
    }
}
