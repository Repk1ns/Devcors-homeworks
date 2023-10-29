package com.devcors.javaacademy.lesson6.rest;

import com.devcors.javaacademy.lesson6.data.dto.UserDTO;
import com.devcors.javaacademy.lesson6.data.entity.User;
import com.devcors.javaacademy.lesson6.data.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }


    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);

        return ResponseEntity.ok("User added");
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO) {
        boolean result = userService.updateUser(id, userDTO);

        if (result) {
            return ResponseEntity.ok("User updated");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        boolean result = userService.deleteUser(id);

        if (result) {
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id) {
        Optional<UserDTO> optionalUserDto = userService.getSpecificUser(id);

        return optionalUserDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
