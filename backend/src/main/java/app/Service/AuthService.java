package app.Service;

import app.HttpDto.AppResponse;
import app.HttpDto.AuthRequest;
import app.HttpDto.RegisterRequest;
import app.HttpDto.UserResponse;
import app.Model.ActivationRequest;
import app.Model.Cart;
import app.Model.Enum.TokenType;
import app.Model.Mapper.UserMapper;
import app.Model.Token;
import app.Model.UserEntity;
import app.Repository.ActivationRequestRepository;
import app.Repository.TokenRepository;
import app.Repository.UserRepository;
import app.Utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import static app.Model.Enum.Role.USER;
import static app.Model.Enum.TokenType.ACCESS_TOKEN;
import static app.Model.Enum.TokenType.REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final ActivationRequestRepository activationRequestRepository;

    private final ActivationRequestService activationRequestService;
    private final EmailService emailService;
    private final JwtUtils jwtUtils;

    private Token buildToken(UserEntity userEntity, TokenType tokenType) {
        String token = tokenType == ACCESS_TOKEN
                ? jwtUtils.generateToken(userEntity)
                : jwtUtils.generateRefreshToken(userEntity);
        return Token.builder()
                .token(token)
                .revoked(false)
                .type(tokenType)
                .build();
    }

    private String generateToken(UserEntity userEntity) {
        Token token = buildToken(userEntity, ACCESS_TOKEN);
        userEntity.setAccessToken(token);
        userRepository.save(userEntity);
        return token.getToken();
    }

    private String generateRefreshToken(UserEntity userEntity) {
        Token token = buildToken(userEntity, REFRESH_TOKEN);
        userEntity.setRefreshToken(token);
        userRepository.save(userEntity);
        return token.getToken();
    }

    private boolean validateToken(UserDetails userDetails, String jwtToken) {
        boolean isTokenValid = jwtUtils.isTokenValid(jwtToken, userDetails);
        boolean isTokenExist = tokenRepository.existsByToken(jwtToken);
        return isTokenExist && isTokenValid;
    }

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
        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .password(password)
                .role(USER)
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .activated(false)
                .tfaEnabled(false)
                .cart(new Cart())
                .addresses(new ArrayList<>())
                .comments(new HashSet<>())
                .orders(new HashSet<>())
                .disabled(false)
                .build();

        userRepository.save(userEntity);
        String jwtToken = generateToken(userEntity);
        String refreshToken = generateRefreshToken(userEntity);

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
        String jwtToken = generateToken(userEntity);
        String refreshToken = generateRefreshToken(userEntity);

        return UserResponse.builder()
                .access_token(jwtToken)
                .refresh_token(refreshToken)
                .userDto(UserMapper.mapToDto(userEntity))
                .build();
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer")) {
            return;
        }
        final String refreshToken = header.substring(7);
        final String email = jwtUtils.extractEmail(refreshToken);
        UserEntity userEntity = getUserFromEmail(email);

        if (validateToken(userEntity, refreshToken)) {
            String jwtToken = generateToken(userEntity);
            AppResponse appResponse = AppResponse.builder()
                    .data(Map.of("access_token", jwtToken))
                    .build();

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(
                    response.getOutputStream(),
                    ResponseEntity.ok().body(appResponse)
            );
        }
    }

    public void sendEmail(String jwtToken) {
        final String email = jwtUtils.extractEmail(jwtToken);
        UserEntity userEntity = getUserFromEmail(email);

        if (validateToken(userEntity, jwtToken)) {
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
