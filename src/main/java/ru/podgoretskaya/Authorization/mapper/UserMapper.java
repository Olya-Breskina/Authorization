package ru.podgoretskaya.Authorization.mapper;

import org.springframework.stereotype.Component;
import ru.podgoretskaya.Authorization.dto.UserDTO;
import ru.podgoretskaya.Authorization.entity.UserEntity;

@Component
public class UserMapper {
    public UserDTO toDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBirthdate(user.getBirthdate());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setBirthdate(userDTO.getBirthdate());
        userEntity.setEmail(userDTO.getEmail());
        return userEntity;
    }
}
