package com.machines.machines_api.services.impl.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.machines.machines_api.config.FrontendConfig;
import com.machines.machines_api.models.dto.auth.AuthenticationResponse;
import com.machines.machines_api.models.dto.auth.UserInfo;
import com.machines.machines_api.models.entity.User;
import com.machines.machines_api.security.CustomOAuth2User;
import com.machines.machines_api.services.OAuth2AuthenticationService;
import com.machines.machines_api.services.TokenService;
import com.machines.machines_api.services.UserService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

/**
 * Implementation of the OAuth2AuthenticationService interface responsible for processing OAuth2 user authentication.
 * This services handles the post-login process, including token generation and cookie attachment.
 */
@Service
@RequiredArgsConstructor
public class OAuth2AuthenticationServiceImpl implements OAuth2AuthenticationService {
    private final List<String> SCOPES = List.of("email", "profile", "openid");
    private final FrontendConfig frontendConfig;
    private final WebClient userInfoClient;
    private final UserService userService;
    private final TokenService tokenService;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    private String clientSecret;

    @Override
    public void processOAuthPostLogin(CustomOAuth2User oAuth2User, Consumer<Cookie> addCookieFunc) {
        // Process OAuth2 user and retrieve associated user entity
        User user = userService.processOAuthUser(oAuth2User);

        tokenService.revokeAllUserTokens(user);

        // Generate authentication response and attach cookies to the response
        AuthenticationResponse authResponse = tokenService.generateAuthResponse(user);
        tokenService.attachAuthCookies(authResponse, addCookieFunc);
    }

    @Override
    public String getOAuthGoogleLoginUrl() {
        return new GoogleAuthorizationCodeRequestUrl(clientId, frontendConfig.getBaseUrl(), SCOPES).build();
    }

    @Override
    public UserInfo processOAuthGoogleLogin(String code) {
        String token = authorizeWithGoogle(code);
        UserInfo userInfo = getUserInfoFromGoogleToken(token);

        return userInfo;
    }

    private UserInfo getUserInfoFromGoogleToken(String googleToken) {
        return userInfoClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth2/v3/userinfo")
                        .queryParam("access_token", googleToken)
                        .build())
                .retrieve()
                .bodyToMono(UserInfo.class)
                .block();
    }

    private String authorizeWithGoogle(String code) {
        try {
            return new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    new GsonFactory(),
                    clientId,
                    clientSecret,
                    code,
                    frontendConfig.getBaseUrl())
                    .execute()
                    .getAccessToken();
        } catch (IOException e) {
            throw new RuntimeException("dsdsa");
        }
    }
}
