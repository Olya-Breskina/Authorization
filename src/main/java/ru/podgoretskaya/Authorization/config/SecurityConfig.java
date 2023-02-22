package ru.podgoretskaya.Authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ru.podgoretskaya.Authorization.enums.UserRole;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,CustomAuthenticationManager customAuthenticationManager) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/admin/**").hasAuthority(UserRole.ADMIN.name())
                        .requestMatchers("/registration").permitAll()
                        .requestMatchers( "/auth").authenticated())
                .httpBasic(Customizer.withDefaults())
                .authenticationManager(customAuthenticationManager);
        return http.build();
    }
}
