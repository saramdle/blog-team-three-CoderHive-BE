package net.blogteamthreecoderhivebe.dto.security;

import lombok.Builder;

import java.util.Map;

@Builder
public record GoogleOauth2Response(
        String sub,
        String name,
        String givenName,
        String familyName,
        String picture,
        String email,
        Boolean emailVerified,
        String locale
) {
    public static GoogleOauth2Response from(Map<String, Object> attributes) {
        return GoogleOauth2Response.builder()
                .sub(String.valueOf(attributes.get("sub")))
                .name(String.valueOf(attributes.get("name")))
                .givenName(String.valueOf(attributes.get("given_name")))
                .familyName(String.valueOf(attributes.get("family_name")))
                .picture(String.valueOf(attributes.get("picture")))
                .email(String.valueOf(attributes.get("email")))
                .emailVerified(Boolean.valueOf(String.valueOf(attributes.get("email_verefied"))))
                .locale(String.valueOf(attributes.get("locale")))
                .build();
    }
}
