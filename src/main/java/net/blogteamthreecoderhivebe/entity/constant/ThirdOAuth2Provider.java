package net.blogteamthreecoderhivebe.entity.constant;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public enum ThirdOAuth2Provider {


    KAKAO {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = getBuilder(
                    registrationId,
                    ClientAuthenticationMethod.CLIENT_SECRET_POST,
                    DEFAULT_REDIRECT_URL
            );
            builder.scope("profile");
            builder.authorizationUri("https://kauth.kakao.com/oauth/authorize");
            builder.tokenUri("https://kauth.kakao.com/oauth/token");
            builder.userNameAttributeName("id");
            builder.clientName("Kakao");

            return builder;
        }
    },
    NAVER {
        @Override
        public ClientRegistration.Builder getBuilder(String registrationId) {
            ClientRegistration.Builder builder = getBuilder(
                    registrationId,
                    ClientAuthenticationMethod.CLIENT_SECRET_POST,
                    DEFAULT_REDIRECT_URL);
            builder.scope("profile");
            builder.authorizationUri("https://nid.naver.com/oauth2.0/authorize");
            builder.tokenUri("https://nid.naver.com/oauth2.0/token");
            builder.userInfoUri("https://openapi.naver.com/v1/nid/me");
            builder.userNameAttributeName("response");//네이버는 response로 지정
            builder.clientName("Naver");

            return builder;
        }
    };

    protected final ClientRegistration.Builder getBuilder(String registrationId, ClientAuthenticationMethod method,
                                                          String redirectUri) {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUri(redirectUri);
        return builder;
    }

    private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/{action}/oauth2/code/{registrationId}";

    public abstract ClientRegistration.Builder getBuilder(String registrationId);
}
