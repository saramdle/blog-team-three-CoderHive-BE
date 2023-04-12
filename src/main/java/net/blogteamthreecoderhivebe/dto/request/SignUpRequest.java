package net.blogteamthreecoderhivebe.dto.request;

public record SignUpRequest(
        String email,
        String nickname,
        long jobId,
        //@Convert(converter = LevelRequestConverter.class)
        //MemberLevel level,
        String level,
        String career
) {

}
