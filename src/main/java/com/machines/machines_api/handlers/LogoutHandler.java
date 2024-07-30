package com.machines.machines_api.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machines.machines_api.exceptions.token.InvalidTokenException;
import com.machines.machines_api.services.TokenService;
import com.machines.machines_api.utils.ObjectMapperHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * LogoutHandler is responsible for handling user logout by invalidating the JWT token and removing associated cookies.
 */
@Service
@RequiredArgsConstructor
public class LogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler {

    private final TokenService tokenService;
    private final ObjectMapper objectMapper;

    /**
     * Performs user logout by invalidating the JWT token and removing associated cookies.
     * If the token is invalid or missing, it sends a standardized error response back to the client.
     */
    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            try {
                ObjectMapperHelper.writeExceptionToObjectMapper(objectMapper, new InvalidTokenException(), response);
                return;
            } catch (IOException exception) {
                return;
            }
        }

        final String jwt = authHeader.substring(7);
        tokenService.logoutToken(jwt);
    }
}
