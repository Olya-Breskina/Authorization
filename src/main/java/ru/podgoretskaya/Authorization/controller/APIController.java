package ru.podgoretskaya.Authorization.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.podgoretskaya.Authorization.dto.ManagementDTO;
import ru.podgoretskaya.Authorization.dto.UserDTO;
import ru.podgoretskaya.Authorization.service.UserService;

import java.util.Arrays;
import java.util.Base64;


@RestController
@RequiredArgsConstructor
@SuppressWarnings("unused")

@Slf4j//логгер
public class APIController {
private final UserService userService;

    @PostMapping(value = "/registration")// создание пользователя
    public void getAuthorization(@RequestBody UserDTO model) {
       userService.registration(model);
    }
    @PostMapping(value = "/auth")// проверка логина и пароля
    public void getLogin(HttpServletRequest request) {//@RequestBody String login, @RequestBody String password
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info(header);
        String trim = header.substring("Basic".length()).trim();
        String decode = new String(Base64.getDecoder().decode(trim));
        String[] split = decode.split(":");

        log.info(decode);
        userService.login(split[0],split[1]);//login,password

    }
    @PostMapping(value = "/admin")// админ создает пользователя
    public void getAuthorizationTeacher(@RequestBody ManagementDTO model) {
        userService.registrationManagement(model);
    }

}