package ru.podgoretskaya.Authorization.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.Authorization.dto.UserDTO;
import ru.podgoretskaya.Authorization.entity.UserEntity;
import ru.podgoretskaya.Authorization.enums.UserRole;
import ru.podgoretskaya.Authorization.mapper.UserMapper;
import ru.podgoretskaya.Authorization.repository.UserRepo;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

import static ru.podgoretskaya.Authorization.enums.UserRole.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public void registration(UserDTO userDTO) {
        UserEntity userEntity = userMapper.toEntity(userDTO);
        userEntity.setRole(STUDENT);
        userEntity.setPassword(encryptedPassword(userEntity.getPassword()));
        userRepo.save(userEntity);
    }

    @Override
    public UserRole login(String login, String password) {
        UserEntity userEntity = userRepo.findByUsername(login).orElseThrow(IllegalArgumentException::new);
        if (userEntity.getUsername().equals(login)&&
                (userEntity.getPassword().equals(encryptedPassword(password)))) {
            return userEntity.getRole();
        }
        else   return ANONYMOUS;
    }


    @Override
    public void registrationTeacher(UserDTO userDTO) {
        UserEntity userEntity = userMapper.toEntity(userDTO);
        userEntity.setRole(TEACHER);
        userRepo.save(userEntity);
    }

    private String encryptedPassword(String password) {
        return new String(Base64.getEncoder().encode(password.getBytes(StandardCharsets.UTF_8)));
    }
}
