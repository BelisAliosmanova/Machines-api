package com.machines.machines_api.services;

import com.machines.machines_api.security.CustomOAuth2User;
import jakarta.servlet.http.Cookie;

import java.util.function.Consumer;

public interface OAuth2AuthenticationService {
    boolean processOAuthPostLogin(CustomOAuth2User oAuth2User, Consumer<Cookie> addCookieFunc) throws Exception;
}
