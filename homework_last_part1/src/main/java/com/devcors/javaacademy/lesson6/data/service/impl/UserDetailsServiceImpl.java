package com.devcors.javaacademy.lesson6.data.service.impl;

import com.devcors.javaacademy.lesson6.data.entity.User;
import com.devcors.javaacademy.lesson6.data.entity.enums.UserRole;
import com.devcors.javaacademy.lesson6.data.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    @PostConstruct
    void init() {
        User user = User.builder()
                .username("admin")
                .firstname("Kha")
                .lastname("Zix")
                .email("KhaZix@gmail.com")
                .address("Brno")
                .password(passwordEncoder.encode("admin"))
                .role(UserRole.ADMIN)
                .build();

        User user2 = User.builder()
                .username("user")
                .firstname("Nasus")
                .lastname("Ren")
                .email("nasus@gmail.com")
                .address("Egypt")
                .password(passwordEncoder.encode("user"))
                .role(UserRole.USER)
                .build();

        userRepository.save(user);
        userRepository.save(user2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
