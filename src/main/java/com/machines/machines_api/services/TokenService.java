package com.machines.machines_api.services;

import com.machines.machines_api.enums.TokenType;
import com.machines.machines_api.models.dto.auth.AuthenticationResponse;
import com.machines.machines_api.models.entity.Token;
import com.machines.machines_api.models.entity.User;
import jakarta.servlet.http.Cookie;

import java.util.List;
import java.util.function.Consumer;

public interface TokenService {
    Token findByToken(String jwt);

    List<Token> findByUser(User user);

    void saveToken(User user, String jwtToken, TokenType tokenType);

    void revokeToken(Token token);

    void revokeAllUserTokens(User user);

    void logoutToken(String jwt);

    AuthenticationResponse generateAuthResponse(User user);

    void createVerificationToken(User user, String token);

    void clearVerificationTokensByUser(User user);
}
