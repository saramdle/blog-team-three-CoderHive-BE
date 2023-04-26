package net.blogteamthreecoderhivebe.global.auth.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record SocialLoginDto( // String nickname,
                              String email) {
    public static SocialLoginDto of(String registrationId, Map<String, Object> attributes) {
        if (registrationId.toUpperCase().equals("KAKAO")) {
            return fromKakao(KakaoOAuth2Response.from(attributes));
        }
        if (registrationId.toUpperCase().equals("NAVER")) {
            return fromNaver(NaverOAuth2Response.from(attributes));
        }
        return fromGoogle(GoogleOAuth2Response.from(attributes));
    }

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
