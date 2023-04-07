package net.blogteamthreecoderhivebe.dto.security;

import lombok.Builder;

@Builder
public record SocialLoginDto(//String nickname,
                             String email) {
    public static SocialLoginDto fromKakao(KakaoOAuth2Response kakaoResponse) {
        return SocialLoginDto.builder()
                //.nickname(kakaoResponse.nickname())
                .email(kakaoResponse.email())
                .build();
    }

    public static SocialLoginDto fromNaver(NaverOAuth2Response naverResponse) {

        return SocialLoginDto.builder()
                //.nickname(naverResponse.nickname())
                .email(naverResponse.email())
                .build();
    }

    public static SocialLoginDto fromGoogle(GoogleOAuth2Response googleResponse) {

        return SocialLoginDto.builder()
                //.nickname(naverResponse.nickname())
                .email(googleResponse.email())
                .build();
    }
}
