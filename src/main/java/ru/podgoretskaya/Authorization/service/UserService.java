package ru.podgoretskaya.Authorization.service;

import ru.podgoretskaya.Authorization.dto.ManagementDTO;
import ru.podgoretskaya.Authorization.dto.UserDTO;
import ru.podgoretskaya.Authorization.enums.UserRole;

public interface UserService {
    void registration(UserDTO userDTO);
  UserRole login(String login, String password);

    void registrationManagement(ManagementDTO managementDTO);
}
