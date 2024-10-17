package com.kdt.wolf.global.filter;

import static org.springframework.util.StringUtils.hasText;

import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.auth.dto.UserRoleType;
import com.kdt.wolf.global.exception.UnauthorizedException;
import com.kdt.wolf.global.auth.provider.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String token = null;

        if (session != null) {
            token = (String) session.getAttribute("JWT");
        }

        if (token != null) {
            jwtTokenProvider.validateToken(token);
            try {
                processTokenAuthentication(token);
            } catch (UnauthorizedException e) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
            return;
        }

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            extractToken(authorization)
                    .ifPresentOrElse(
                            this::processTokenAuthentication,
                            () -> {
                                // TODO :  토큰이 없는 경우 동작 처리 해야할까?
                            });

            filterChain.doFilter(request, response);
        } catch (UnauthorizedException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    private Optional<String> extractToken(String authorization) { // resolve AccessToken
        if (hasText(authorization) && Pattern.matches("^Bearer .*", authorization)) {
            String value = authorization.replaceAll("^Bearer( )*", "");

            return hasText(value) ? Optional.of(value) : Optional.empty();
        }

        return Optional.empty();
    }

    private Authentication getAuthentication(String subject, UserRoleType userType) {
        long userId;
        try {
            userId = Long.parseLong(subject);
        } catch (NumberFormatException e) {
            throw new UnauthorizedException();
        }
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UnauthorizedException::new);

        AuthenticatedUser authenticatedUser = new AuthenticatedUser(user, userType);

        return new UsernamePasswordAuthenticationToken(
                authenticatedUser, null, authenticatedUser.getAuthorities());
    }

    private void processTokenAuthentication(String token) {
        jwtTokenProvider.validateToken(token);

        Authentication authentication = getAuthentication(
                jwtTokenProvider.getSubject(token),
                jwtTokenProvider.getUserType(token));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
