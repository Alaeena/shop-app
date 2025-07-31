package app.model.mapper;

import app.model.dto.UserDto;
import app.model.postgres.UserEntity;

public class UserMapper {

    public static UserDto mapToDto(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .activated(user.getActivated())
                .email(user.getEmail())
                .tfaEnabled(user.getTfaEnabled())
                .role(user.getRole())
                .shop(user.getShop() != null ? user.getShop().getId() : null)
                .build();
    }
}
