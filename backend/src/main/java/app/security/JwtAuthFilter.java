package app.security;

import app.httpDto.AppResponse;
import app.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static app.model.enums.TokenType.ACCESS_TOKEN;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getServletPath().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwtToken;
        final String email;

        if (header == null || !header.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            jwtToken = header.substring(7);
            email = jwtUtils.extractEmail(jwtToken);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                boolean isTokenValid = jwtUtils.isTokenValid(jwtToken, userDetails, ACCESS_TOKEN);

                if (isTokenValid) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            }
        } catch (Exception ex) {
            AppResponse apiError = AppResponse.builder()
                    .message("Jwt authentication exception: " + ex.getMessage())
                    .status(HttpStatus.BAD_REQUEST)
                    .build();

            response.setHeader("error", ex.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            new ObjectMapper().writeValue(
                    response.getOutputStream(),
                    ResponseEntity.badRequest().body(apiError)
            );
            return;
        }
        filterChain.doFilter(request, response);
    }
}
