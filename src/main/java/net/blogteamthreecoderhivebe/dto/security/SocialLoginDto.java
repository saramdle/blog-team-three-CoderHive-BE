package net.blogteamthreecoderhivebe.dto.security;

import lombok.Builder;

@Builder
public record SocialLoginDto(String nickname,
                             String email) {
    public static SocialLoginDto fromKakao(KakaoOAuth2Response kakaoResponse) {
        return SocialLoginDto.builder()
                .nickname(kakaoResponse.nickname())
                .email(kakaoResponse.nickname())
                .build();
    }

    public static SocialLoginDto fromNaver(KakaoOAuth2Response kakaoResponse) {
        return null;
    }

    public static SocialLoginDto fromGoogle(KakaoOAuth2Response kakaoResponse) {
        return null;
    }
}
