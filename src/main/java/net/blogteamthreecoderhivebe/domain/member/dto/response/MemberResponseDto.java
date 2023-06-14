package net.blogteamthreecoderhivebe.domain.member.dto.response;

public class MemberResponseDto {

    /**
     * 게시글 상세페이지에 보여줄 회원 정보
     */
    public record MemberInfoOnPostDetail(
            Long memberId,
            String profileImgUrl,
            String nickname,
            String job,
            String level
    ) {
    }
}
