package com.devcors.javaacademy.lesson6.data.service;

import com.devcors.javaacademy.lesson6.data.dto.UserDTO;
import com.devcors.javaacademy.lesson6.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<UserDTO> getSpecificUser(Integer id);
    void addUser(UserDTO user);
    boolean updateUser(Integer id, UserDTO userDTO);
    boolean deleteUser(Integer id);
}
