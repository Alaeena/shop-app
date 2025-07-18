package app.config;


import app.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;
    private final LogoutHandler logoutHandler;
    @Value("${server.client-url}")
    private String clientUrl;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(configurationSource()))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/", "/auth/**", "/product/**", "/category/**", "/vnpay/**").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .oauth2Login(oAuth2Login -> oAuth2Login.loginPage("/auth/oauth2-login"))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) ->
                                SecurityContextHolder.clearContext()
                        )
                )
        ;
        return http.build();
    }

    public CorsConfigurationSource configurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of(clientUrl));
        config.setAllowedHeaders(List.of(
                ORIGIN,
                CONTENT_TYPE,
                ACCEPT,
                AUTHORIZATION,
                ACCEPT_LANGUAGE,
                ACCEPT_ENCODING
        ));
        config.setAllowedMethods(List.of(
                GET.name(),
                POST.name(),
                DELETE.name(),
                PUT.name(),
                PATCH.name()
        ));
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
