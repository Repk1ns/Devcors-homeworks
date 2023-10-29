package com.devcors.javaacademy.lesson6.data.service.impl;

import com.devcors.javaacademy.lesson6.data.dto.UserDTO;
import com.devcors.javaacademy.lesson6.data.entity.User;
import com.devcors.javaacademy.lesson6.data.repository.UserRepository;
import com.devcors.javaacademy.lesson6.data.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public Optional<UserDTO> getSpecificUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            UserDTO userDTO = userMapper.EntityToDto(optionalUser.get());

            return Optional.of(userDTO);
        } else {

            return Optional.empty();
        }
    }


    public void addUser(UserDTO userDTO) {
        User user = userMapper.DtoToEntity(userDTO, null);

        userRepository.save(user);
    }


    @Override
    public boolean updateUser(Integer id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = userMapper.DtoToEntity(userDTO, optionalUser.get());

            userRepository.save(user);

            return true;
        } else {

            return false;
        }
    }


    public boolean deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);

            return true;
        } else {

            return false;
        }
    }
}
