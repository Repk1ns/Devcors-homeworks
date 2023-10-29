package com.devcors.javaacademy.lesson6.data.service.impl;

import com.devcors.javaacademy.lesson6.data.dto.UserDTO;
import com.devcors.javaacademy.lesson6.data.entity.User;
import com.devcors.javaacademy.lesson6.data.service.EntityMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements EntityMapper<User, UserDTO> {


    public User DtoToEntity(UserDTO userDTO, User user) {
        if (user == null) {
            user = new User();
        }

        user.setEmail(userDTO.getEmail());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setAddress(userDTO.getAddress());
        user.setRole(userDTO.getRole());

        return user;
    }


    public UserDTO EntityToDto(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .address(user.getAddress())
                .role(user.getRole())
                .build();
    }
}
