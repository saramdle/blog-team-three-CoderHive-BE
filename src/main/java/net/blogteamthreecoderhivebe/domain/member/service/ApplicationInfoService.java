package net.blogteamthreecoderhivebe.domain.member.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.member.entity.ApplicationInfo;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.member.repository.ApplicationInfoRepository;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApplicationInfoService {

    private final ApplicationInfoRepository applicationInfoRepository;

    /**
     * 회원의 지원 정보 조회
     */
    public List<ApplicationInfo> searchApplyPost(Long memberId) {
        return applicationInfoRepository.findByMember_Id(memberId);
    }

    /**
     * 회원이 모집직무에 지원한 결과 조회
     */
    public ApplicationResult getApplyResult(Member member, RecruitmentJob recruitmentJob) {
        Optional<ApplicationResult> applyResult = applicationInfoRepository.findApplyResult(member, recruitmentJob);
        // 지원 이력이 없을 경우 미신청
        return applyResult.orElse(ApplicationResult.NONE);
    }
}
