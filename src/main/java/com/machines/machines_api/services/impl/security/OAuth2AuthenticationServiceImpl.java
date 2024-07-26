package com.machines.machines_api.services.impl.security;

import com.machines.machines_api.models.dto.auth.AuthenticationResponse;
import com.machines.machines_api.models.entity.User;
import com.machines.machines_api.security.CustomOAuth2User;
import com.machines.machines_api.services.OAuth2AuthenticationService;
import com.machines.machines_api.services.TokenService;
import com.machines.machines_api.services.UserService;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * Implementation of the OAuth2AuthenticationService interface responsible for processing OAuth2 user authentication.
 * This services handles the post-login process, including token generation and cookie attachment.
 */
@Service
@AllArgsConstructor
public class OAuth2AuthenticationServiceImpl implements OAuth2AuthenticationService {
    private final UserService userService;
    private final TokenService tokenService;

    @Override
    public void processOAuthPostLogin(CustomOAuth2User oAuth2User, Consumer<Cookie> addCookieFunc) {
        // Process OAuth2 user and retrieve associated user entity
        User user = userService.processOAuthUser(oAuth2User);

        tokenService.revokeAllUserTokens(user);

        // Generate authentication response and attach cookies to the response
        AuthenticationResponse authResponse = tokenService.generateAuthResponse(user);
        tokenService.attachAuthCookies(authResponse, addCookieFunc);
    }
}
