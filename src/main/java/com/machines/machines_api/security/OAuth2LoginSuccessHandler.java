package com.machines.machines_api.security;

import com.machines.machines_api.config.FrontendConfig;
import com.machines.machines_api.services.OAuth2AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * OAuth2LoginSuccessHandler handles successful OAuth2 login authentication.
 * It redirects users to different URLs based on whether additional information is required after login.
 */
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final OAuth2AuthenticationService oAuth2AuthenticationService;
    private final FrontendConfig frontendConfig;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        // Retrieve the OAuth2 user details from the authentication object
        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
        oAuth2AuthenticationService.processOAuthPostLogin(oauthUser, response::addCookie);

        try {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}