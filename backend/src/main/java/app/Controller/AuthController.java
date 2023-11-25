package app.Controller;


import app.HttpDto.AppResponse;
import app.HttpDto.AuthRequest;
import app.HttpDto.RegisterRequest;
import app.HttpDto.UserResponse;
import app.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private void checkError(BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result
                    .getAllErrors().stream()
                    .map(item -> item.getDefaultMessage())
                    .findFirst().get()
            );
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest registerRequest,
            BindingResult result
    ) {
        checkError(result);
        UserResponse response = authService.registerUser(registerRequest);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @Valid @RequestBody AuthRequest authRequest,
            BindingResult result
    ) {
        checkError(result);
        UserResponse response = authService.loginUser(authRequest);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/refresh")
    public void refresh(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refreshToken(request, response);
    }

    @GetMapping("/activate")
    public ResponseEntity<AppResponse> sendEmail(
            @NotNull @RequestHeader(name = "Authorization") String header
    ) {
        if (header == null || !header.startsWith("Bearer")) {
            return null;
        }
        final String jwtToken = header.substring(7);
        authService.sendEmail(jwtToken);

        return ResponseEntity.ok().body(
                AppResponse.builder()
                        .message("Email sent !!!")
                        .build()
        );
    }

    @GetMapping("/activate-email")
    public ResponseEntity<AppResponse> confirmEmail(
            @NotNull @RequestParam("token") String token
    ) {
        authService.confirmEmail(token);
        return ResponseEntity.ok().body(
                AppResponse.builder()
                        .message("Confirmed!!")
                        .build()
        );
    }


    @GetMapping("/verify")
    public ResponseEntity<AppResponse> verify(
            @PathParam("code") String code
    ) {

        return null;
    }


}
