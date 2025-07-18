package app.controller;

import app.model.dto.UserDto;
import app.model.mapper.UserMapper;
import app.model.UserEntity;
import app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Map<String, UserDto> getUserInfo(@AuthenticationPrincipal UserEntity user) {
        return Map.of(
                "Data",
                UserMapper.mapToDto(user)
        );
    }

    @DeleteMapping("/{id}")
    public UserDto deleteUser(
            @PathVariable(name = "id") Long id
    ) {
        return userService.deleteUser(id);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUser() {
        return userService.findAll();
    }

    @PostMapping
    public Map<String, UserDto> updateUserInfo(
            @AuthenticationPrincipal UserEntity user,
            @Valid @ModelAttribute UserDto userDto
    ) {
        return Map.of(
                "Data",
                userService.updateInfo(userDto, user)
        );
    }
}
