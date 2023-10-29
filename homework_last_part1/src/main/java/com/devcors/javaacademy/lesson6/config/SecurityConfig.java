package com.devcors.javaacademy.lesson6.config;

import com.devcors.javaacademy.lesson6.data.entity.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    /**
     * Due to BCrypt
     */
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }


    /**
     * Spring Security 6+ in SpringBoot 3.1.1.
     * Useful link: https://www.youtube.com/watch?v=1Mel8wn1HZs
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET,"/cars").permitAll();
            auth.requestMatchers(HttpMethod.GET,"/cars/filter").permitAll();
            auth.requestMatchers("/cars/**").hasAnyRole(UserRole.ADMIN.name());
            auth.requestMatchers("/users/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name());
            auth.anyRequest().authenticated();
        });

        http.csrf(c -> c.ignoringRequestMatchers("/cars/**", "/users/**"));

        http.cors(c -> c.configurationSource((req -> {
            CorsConfiguration conf = new CorsConfiguration();
            conf.setAllowedMethods(List.of("*"));

            return conf;
        })));

        http.httpBasic(Customizer.withDefaults());

        http.formLogin(Customizer.withDefaults());

        http.userDetailsService(userDetailsService);
        http.authenticationProvider(authProvider());

        return http.build();
    }

}
