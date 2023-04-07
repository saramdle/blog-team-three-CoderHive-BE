package net.blogteamthreecoderhivebe.dto.security;

import lombok.Builder;

import java.util.Map;

@Builder
public record NaverOAuth2Response(
        String resultcode,
        String message,
        NaverResponse naverResponse
) {
    @Builder
    public record NaverResponse(
            String id,
            String nickname,
            String name,
            String email,
            String gender,
            String age,
            String birthday,
            String profile_image,
            String birthyear,
            String mobile
    ) {
        public static NaverResponse from(Map<String, Object> attributes) {
            return NaverResponse.builder()
                    .id(String.valueOf(attributes.get("id")))
                    .nickname(String.valueOf(attributes.get("nickname")))
                    .name(String.valueOf(attributes.get("name")))
                    .email(String.valueOf(attributes.get("email")))
                    .gender(String.valueOf(attributes.get("gender")))
                    .age(String.valueOf(attributes.get("age")))
                    .birthday(String.valueOf(attributes.get("birthday")))
                    .profile_image(String.valueOf(attributes.get("profile_image")))
                    .birthday(String.valueOf(attributes.get("birthday")))
                    .mobile(String.valueOf(attributes.get("mobile")))
                    .build();
        }
    }

    public static NaverOAuth2Response from(Map<String, Object> attributes) {
        return NaverOAuth2Response.builder()
                .resultcode(String.valueOf(attributes.get("resultcode")))
                .message(String.valueOf(attributes.get("message")))
                .naverResponse(NaverResponse.from((Map<String, Object>) attributes.get("response")))
                .build();
    }

    public String email() { return this.naverResponse().email(); }
    public String nickname() { return this.naverResponse().nickname(); }

}
