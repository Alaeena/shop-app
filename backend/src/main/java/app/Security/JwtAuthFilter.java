package app.Security;

import app.HttpDto.AppResponse;
import app.Model.Token;
import app.Repository.TokenRepository;
import app.Utils.JwtUtils;
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
import java.util.Optional;

import static app.Model.Enum.TokenType.ACCESS_TOKEN;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final TokenRepository tokenRepository;
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
                Optional<Token> optionalToken = tokenRepository.findByToken(jwtToken);

                boolean isTokenValid = jwtUtils.isTokenValid(jwtToken, userDetails);
                boolean isTokenExist = optionalToken.isPresent();
                boolean isAccessToken = optionalToken
                        .map(item -> item.getType() == ACCESS_TOKEN)
                        .orElse(false);
                boolean isTokenRevoked = optionalToken
                        .map(item -> item.isRevoked())
                        .orElse(false);

                if (isTokenValid && isTokenExist && !isTokenRevoked && isAccessToken) {
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
