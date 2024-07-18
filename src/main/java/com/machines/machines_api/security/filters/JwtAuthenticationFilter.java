package com.machines.machines_api.security.filters;

import com.machines.machines_api.exceptions.user.UserNotFoundException;
import com.machines.machines_api.models.dto.auth.PublicUserDTO;
import com.machines.machines_api.repositories.TokenRepository;
import com.machines.machines_api.services.JwtService;
import com.machines.machines_api.services.UserService;
import com.machines.machines_api.utils.CookieHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.machines.machines_api.services.impl.security.TokenServiceImpl.AUTH_COOKIE_KEY_JWT;

/**
 * Filter responsible for JWT-based authentication.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Key to retrieve user information from request attribute.
     */
    public static final String userKey = "user";
    private final JwtService jwtService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String path = request.getServletPath();

        // Skip authentication for specific paths related to authentication process
        if (path.contains("/api/v1/auth") && !path.contains("/api/v1/auth/complete-oauth")) {
            filterChain.doFilter(request, response);
            return;
        }

        request.setAttribute(userKey, null);

        final String jwt = CookieHelper.readCookie(AUTH_COOKIE_KEY_JWT, request.getCookies()).orElse(null);

        if (jwt == null || jwt.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        final String userEmail = jwtService.extractUsername(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails;

            try {
                userDetails = userService.findByEmail(userEmail);
            } catch (UserNotFoundException exception) {
                filterChain.doFilter(request, response);
                return;
            }

            // Check if token is valid and not revoked or expired
            boolean isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);

            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {

                // Set user authentication in security context
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            // Set user details in request attribute
            request.setAttribute(userKey, modelMapper.map(userDetails, PublicUserDTO.class));
        }

        filterChain.doFilter(request, response);
    }
}
