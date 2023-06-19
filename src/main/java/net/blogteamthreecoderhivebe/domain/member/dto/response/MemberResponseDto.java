package net.blogteamthreecoderhivebe.domain.member.dto.response;

import lombok.Builder;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;

public class MemberResponseDto {

    /**
     * 게시글 상세페이지에 보여줄 회원 정보
     */
    @Builder
    public record MemberInfoOnPostDetail(
            Long memberId,
            String profileImgUrl,
            String nickname,
            String job,
            String level
    ) {
        public static MemberInfoOnPostDetail from(Member member) {
            return MemberInfoOnPostDetail.builder()
                    .memberId(member.getId())
                    .profileImgUrl(member.getProfileImageUrl())
                    .nickname(member.getNickname())
                    .job(member.getJob().getDetail())
                    .level(member.getLevel().getDescription())
                    .build();
        }
    }
}
