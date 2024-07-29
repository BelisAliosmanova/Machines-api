package com.machines.machines_api.services;

import com.machines.machines_api.models.dto.auth.UserInfo;
import com.machines.machines_api.security.CustomOAuth2User;
import jakarta.servlet.http.Cookie;

import java.util.function.Consumer;

public interface OAuth2AuthenticationService {
    void processOAuthPostLogin(CustomOAuth2User oAuth2User, Consumer<Cookie> addCookieFunc);

    String getOAuthGoogleLoginUrl();

    UserInfo processOAuthGoogleLogin(String code);
}
