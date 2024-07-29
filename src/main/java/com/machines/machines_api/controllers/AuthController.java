package com.machines.machines_api.controllers;

import com.machines.machines_api.models.dto.auth.UserInfo;
import com.machines.machines_api.services.OAuth2AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final OAuth2AuthenticationService oAuth2AuthenticationService;

    @GetMapping("/auth/url")
    public ResponseEntity<String> auth() {
        return ResponseEntity.ok(oAuth2AuthenticationService.getOAuthGoogleLoginUrl());
    }

    @GetMapping("/auth/callback")
    public ResponseEntity<UserInfo> callback(@RequestParam("code") String code) {
        return ResponseEntity.ok(oAuth2AuthenticationService.processOAuthGoogleLogin(code));
    }
}
