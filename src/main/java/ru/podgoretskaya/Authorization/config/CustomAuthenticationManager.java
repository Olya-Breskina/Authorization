package ru.podgoretskaya.Authorization.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.podgoretskaya.Authorization.enums.UserRole;
import ru.podgoretskaya.Authorization.service.UserService;

import java.util.Collections;

@RequiredArgsConstructor
@Component
//получаю от пользователя
public class CustomAuthenticationManager implements AuthenticationManager {
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = String.valueOf(authentication.getPrincipal());//логин
        String password = String.valueOf(authentication.getCredentials());//пароль
        UserRole role = userService.login(login, password);
        if (role.equals(UserRole.ANONYMOUS)) {
            return authentication;
        }
        return new UsernamePasswordAuthenticationToken(login, password, Collections.singletonList(new SimpleGrantedAuthority(role.name())));

    }
}
