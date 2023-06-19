package net.blogteamthreecoderhivebe.domain.post.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;

public class RecruitJobResponseDto {

    /**
     * 게시글 상세페이지에 보여줄 모집 정보
     */
    @Builder
    public record RecruitInfoOnPostDetail(
            Long recruitJobId,
            String jobDetail,
            int number, // 모집 인원
            int passNumber, // 모집 완료된 인원
            String applyResult // 회원의 지원 현황
    ) {
        public static RecruitInfoOnPostDetail from(RecruitmentJob recruitmentJob, ApplicationResult applyResult) {
            return RecruitInfoOnPostDetail.builder()
                    .recruitJobId(recruitmentJob.getId())
                    .jobDetail(recruitmentJob.getJob().getDetail())
                    .number(recruitmentJob.getNumber())
                    .passNumber(recruitmentJob.getPassNumber())
                    .applyResult(applyResult.getDescription())
                    .build();
        }
    }
}
