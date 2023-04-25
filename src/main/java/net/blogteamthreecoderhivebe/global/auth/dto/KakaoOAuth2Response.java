package net.blogteamthreecoderhivebe.global.auth.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Builder
@SuppressWarnings("unchecked") // TODO: Map -> Object 변환 로직이 있어 제네릭 타입 캐스팅 문제를 무시한다. 더 좋은 방법이 있다면 고려할 수 있음.
public record KakaoOAuth2Response(
        Long id,
        LocalDateTime connectedAt,
        Map<String, Object> properties,
        KakaoAccount kakaoAccount
) {

    @Builder
    public record KakaoAccount(
            Boolean profileNicknameNeedsAgreement,
            Profile profile,
            Boolean hasEmail,
            Boolean emailNeedsAgreement,
            Boolean isEmailValid,
            Boolean isEmailVerified,
            String email
    ) {
        @Builder
        public record Profile(String nickname) {
            public static Profile from(Map<String, Object> attributes) {
                return Profile.builder()
                        .nickname(String.valueOf(attributes.get("nickname")))
                        .build();
            }
        }

        public static KakaoAccount from(Map<String, Object> attributes) {
            return KakaoAccount.builder()
                    .profileNicknameNeedsAgreement(Boolean.valueOf(String.valueOf(attributes.get("profile_nickname_needs_agreement")))) //value.of 은 null 일 때 "null"을 반환한다.
                    .profile(Profile.from((Map<String, Object>) attributes.get("profile")))
                    .hasEmail(Boolean.valueOf(String.valueOf(attributes.get("has_email"))))
                    .emailNeedsAgreement(Boolean.valueOf(String.valueOf(attributes.get("email_needs_agreement"))))
                    .isEmailValid(Boolean.valueOf(String.valueOf(attributes.get("is_email_valid"))))
                    .isEmailVerified(Boolean.valueOf(String.valueOf(attributes.get("is_email_verified"))))
                    .email(String.valueOf(attributes.get("email")))
                    .build();
        }

        public String nickname() { return this.profile().nickname(); }
    }

    public static KakaoOAuth2Response from(Map<String, Object> attributes) {
        return KakaoOAuth2Response.builder()
                .id(Long.valueOf(String.valueOf(attributes.get("id"))))
                .connectedAt(LocalDateTime.parse(
                        String.valueOf(attributes.get("connected_at")),
                        DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault())
                ))
                .properties((Map<String, Object>) attributes.get("properties"))
                .kakaoAccount(KakaoAccount.from((Map<String, Object>) attributes.get("kakao_account")))
                .build();
    }

    public String email() { return this.kakaoAccount().email(); }
    public String nickname() { return this.kakaoAccount().nickname(); }
}
