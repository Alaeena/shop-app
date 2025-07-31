package app.service;

import app.httpDto.AppResponse;
import app.httpDto.AuthRequest;
import app.httpDto.RegisterRequest;
import app.httpDto.UserResponse;
import app.model.mapper.UserMapper;
import app.model.postgres.ActivationRequest;
import app.model.postgres.UserEntity;
import app.repository.postgres.ActivationRequestRepository;
import app.repository.postgres.UserRepository;
import app.utils.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static app.model.enums.TokenType.ACCESS_TOKEN;
import static app.model.enums.TokenType.REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ActivationRequestRepository activationRequestRepository;
    private final RedisTemplate<String, String> redisTemplate;

    private final ActivationRequestService activationRequestService;
    private final EmailService emailService;
    private final JwtUtils jwtUtils;

    private UserEntity getUserFromEmail(String email) {
        if (email == null) {
            throw new RuntimeException("No email was found in the token");
        }
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }

    public UserResponse registerUser(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();
        String rePassword = registerRequest.getRePassword();

        if (!password.equals(rePassword)) {
            throw new RuntimeException("You typed in two different password");
        }
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException(String.format("Email: %s already existed", email));
        }
        UserEntity userEntity = new UserEntity(registerRequest);
        userRepository.save(userEntity);
        String jwtToken = jwtUtils.buildToken(userEntity, ACCESS_TOKEN);
        String refreshToken = jwtUtils.buildToken(userEntity, REFRESH_TOKEN);

        return UserResponse.builder()
                .access_token(jwtToken)
                .refresh_token(refreshToken)
                .userDto(UserMapper.mapToDto(userEntity))
                .build();
    }

    public UserResponse loginUser(AuthRequest authRequest) {
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();
        UserEntity userEntity = getUserFromEmail(email);

        if (!userEntity.getPassword().equals(password)) {
            throw new RuntimeException("Unknown password or email");
        }
        String jwtToken = jwtUtils.buildToken(userEntity, ACCESS_TOKEN);
        String refreshToken = jwtUtils.buildToken(userEntity, REFRESH_TOKEN);
        return UserResponse.builder()
                .access_token(jwtToken)
                .refresh_token(refreshToken)
                .userDto(UserMapper.mapToDto(userEntity))
                .build();
    }

    @Transactional
    public AppResponse refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer")) {
            return null;
        }
        final String refreshToken = header.substring(7);
        final String email = jwtUtils.extractEmail(refreshToken);
        UserEntity userEntity = getUserFromEmail(email);

        if (jwtUtils.isTokenValid(refreshToken, userEntity, REFRESH_TOKEN)) {
            String jwtToken = jwtUtils.buildToken(userEntity, ACCESS_TOKEN);

            return AppResponse.builder()
                    .data(Map.of("access_token", jwtToken))
                    .build();
        }
        return null;
    }

    public void sendEmail(String jwtToken) {
        final String email = jwtUtils.extractEmail(jwtToken);
        UserEntity userEntity = getUserFromEmail(email);

        if (jwtUtils.isTokenValid(jwtToken, userEntity, ACCESS_TOKEN)) {
            String token = UUID.randomUUID().toString();
            ActivationRequest request = ActivationRequest.builder()
                    .userEntity(userEntity)
                    .token(token)
                    .createdAt(LocalDateTime.now())
                    .expireAt(LocalDateTime.now().plusMinutes(15))
                    .build();
            activationRequestRepository.save(request);
            emailService.sendSimpleMessage(email, email, token);
        }
    }

    public void confirmEmail(String token) {
        ActivationRequest request = activationRequestRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found"));
        if (request.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }
        LocalDateTime expiredAt = request.getExpireAt();
        UserEntity userEntity = request.getUserEntity();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }
        userEntity.setActivated(true);
        userRepository.save(userEntity);
        activationRequestService.setConfirmedAt(token);
    }
}
