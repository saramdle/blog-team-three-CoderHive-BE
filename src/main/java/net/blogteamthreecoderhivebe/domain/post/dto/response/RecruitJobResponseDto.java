package net.blogteamthreecoderhivebe.domain.post.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitJob;

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
        public static RecruitInfoOnPostDetail from(RecruitJob recruitJob, ApplicationResult applyResult) {
            return RecruitInfoOnPostDetail.builder()
                    .recruitJobId(recruitJob.getId())
                    .jobDetail(recruitJob.getJob().getDetail())
                    .number(recruitJob.getNumber())
                    .passNumber(recruitJob.getPassNumber())
                    .applyResult(applyResult.getDescription())
                    .build();
        }
    }
}
