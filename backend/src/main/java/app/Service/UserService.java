package app.Service;


import app.Model.Dto.UserDto;
import app.Model.Enum.Role;
import app.Model.Mapper.UserMapper;
import app.Model.UserEntity;
import app.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto updateInfo(UserDto userDto, UserEntity user) {
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        userRepository.save(user);
        return UserMapper.mapToDto(user);
    }

    public List<UserDto> findAll() {
        return userRepository.findAllByRoleAndDisabled(Role.USER, false)
                .stream().map(UserMapper::mapToDto)
                .toList();
    }

    public UserDto deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found"));
        user.setDisabled(true);
        userRepository.save(user);
        return UserMapper.mapToDto(user);
    }
}
