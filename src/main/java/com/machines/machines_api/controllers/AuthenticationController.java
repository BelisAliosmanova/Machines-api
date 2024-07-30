package com.machines.machines_api.controllers;

import com.machines.machines_api.config.FrontendConfig;
import com.machines.machines_api.interfaces.RateLimited;
import com.machines.machines_api.models.dto.auth.AuthenticationRequest;
import com.machines.machines_api.models.dto.auth.AuthenticationResponse;
import com.machines.machines_api.models.dto.auth.RegisterRequest;
import com.machines.machines_api.models.entity.User;
import com.machines.machines_api.security.filters.JwtAuthenticationFilter;
import com.machines.machines_api.services.AuthenticationService;
import com.machines.machines_api.services.impl.security.events.OnPasswordResetRequestEvent;
import com.machines.machines_api.utils.CookieHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Controller class for handling authentication-related operations.
 * JWT (access and refresh token);
 * OAuth2;
 * Email confirmation;
 * Forgotten password.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final ApplicationEventPublisher eventPublisher;
    private final FrontendConfig frontendConfig;

    @Value("${server.backend.baseUrl}")
    private String appBaseUrl;

    @RateLimited
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.register(request);

        // Email verification eventually
        // User user = modelMapper.map(authenticationResponse.getUser(), User.class);
        // eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, appBaseUrl));

        return ResponseEntity.ok(authenticationResponse);
    }

    //Endpoint for email confirmation during registration
    @GetMapping("/registrationConfirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token, HttpServletResponse httpServletResponse) throws IOException {
        authenticationService.confirmRegistration(token);
        httpServletResponse.sendRedirect(frontendConfig.getLoginUrl());
        return ResponseEntity.ok("User registration confirmed successfully!");
    }

    @RateLimited
    @PostMapping("/authenticate") // login
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse servletResponse) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        return ResponseEntity.ok(authenticationResponse);
    }

    @GetMapping("/refresh-token/{refreshToken}")
    public ResponseEntity<AuthenticationResponse> refreshToken(@PathVariable String refreshToken) throws IOException {
        AuthenticationResponse authenticationResponse = authenticationService.refreshToken(refreshToken);
        return ResponseEntity.ok(authenticationResponse);
    }

    @GetMapping("/me") // Retrieves current user information.
    public ResponseEntity<AuthenticationResponse> getMe(HttpServletRequest request) {
        String jwtToken = (String) request.getAttribute(JwtAuthenticationFilter.JWT_KEY);
        AuthenticationResponse authenticationResponse = authenticationService.me(jwtToken);

        return ResponseEntity.ok(authenticationResponse);
    }

    @RateLimited
    @PostMapping("/forgot-password") // Sends link to email so the user can change their password
    public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
        User user = authenticationService.forgotPassword(email);
        eventPublisher.publishEvent(new OnPasswordResetRequestEvent(user, appBaseUrl));
        return ResponseEntity.ok("Password reset link sent to your email!");
    }

    @RateLimited
    @PostMapping("/password-reset")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword) {
        authenticationService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }
}
