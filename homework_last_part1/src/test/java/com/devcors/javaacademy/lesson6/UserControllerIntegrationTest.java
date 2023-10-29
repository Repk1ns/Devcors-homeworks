package com.devcors.javaacademy.lesson6;

import com.devcors.javaacademy.lesson6.data.entity.enums.UserRole;
import com.devcors.javaacademy.lesson6.data.entity.User;
import com.devcors.javaacademy.lesson6.data.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Lesson6Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class UserControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserRepository userRepository;

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL_1 = "admin@email.com";
    public static final String FIRSTNAME_1 = "Firstname";
    public static final String LASTNAME_1 = "Lastname";
    public static final String ADDRESS_1 = "Downing street 10";

    public static final String EMAIL_2 = "user@email.com";

    private static final User USER_1 = User.builder()
            .username(USERNAME)
            .password(PASSWORD)
            .email(EMAIL_1)
            .firstname(FIRSTNAME_1)
            .lastname(LASTNAME_1)
            .role(UserRole.ADMIN)
            .address(ADDRESS_1)
            .build();

    private static final User USER_2 = User.builder()
            .username("username2")
            .password("password2")
            .email(EMAIL_2)
            .firstname("Firstname")
            .lastname("Lastname")
            .role(UserRole.USER)
            .address("Baker street 10")
            .build();

    @AfterEach
    void afterEach() {
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void createUserShouldReturnOk() {
        webTestClient.post()
                .uri(ub -> ub.path("/users").build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "user").getBytes(StandardCharsets.UTF_8)))
                .bodyValue(USER_1)
                .exchange()
                .expectStatus()
                .isOk();

        List<User> users = userRepository.findAll();
        assertFalse(CollectionUtils.isEmpty(users));
        assertEquals(5, users.size());
        User user = users.get(2);

        assertEquals(EMAIL_1, user.getEmail());
        assertEquals(FIRSTNAME_1, user.getFirstname());
        assertEquals(LASTNAME_1, user.getLastname());
        assertEquals(UserRole.ADMIN, user.getRole());
        assertEquals(ADDRESS_1, user.getAddress());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void updateUserShouldReturnOk() {
        User savedUser = userRepository.save(USER_1);
        Integer userId = savedUser.getId();

        savedUser.setId(null);
        savedUser.setRole(UserRole.USER);
        webTestClient.put()
                .uri(ub -> ub.path("/users/" + userId).build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "user").getBytes(StandardCharsets.UTF_8)))
                .bodyValue(savedUser)
                .exchange()
                .expectStatus()
                .isOk();

        Optional<User> user = userRepository.findById(userId);
        assertTrue(user.isPresent());
        assertEquals(UserRole.USER, user.get().getRole());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void updateUserByIdShouldReturnBadRequest() {
        User savedUser = userRepository.save(USER_1);

        webTestClient.put()
                .uri(ub -> ub.path("/users/" + (savedUser.getId() + 42)).build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "user").getBytes(StandardCharsets.UTF_8)))
                .bodyValue(savedUser)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void getAllUsersShouldReturnOk() {
        userRepository.save(USER_1);
        userRepository.save(USER_2);

        List<User> users = webTestClient.get()
                .uri(ub -> ub.path("/users").build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("admin" + ":" + "admin").getBytes(StandardCharsets.UTF_8)))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(User.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(users);
        assertEquals(4, users.size());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void getUserByIdShouldReturnOk() {
        User savedUser = userRepository.save(USER_1);

        User user = webTestClient.get()
                .uri(ub -> ub.path("/users/" + savedUser.getId()).build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "user").getBytes(StandardCharsets.UTF_8)))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(user);
        assertEquals(ADDRESS_1, user.getAddress());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void getUserByIdShouldReturnNotFound() {
        User savedUser = userRepository.save(USER_1);

        webTestClient.get()
                .uri(ub -> ub.path("/users/" + (savedUser.getId() + 1)).build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "user").getBytes(StandardCharsets.UTF_8)))
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void deleteUserShouldReturnOk() {
        User savedUser = userRepository.save(USER_1);
        userRepository.save(USER_2);

        webTestClient.delete()
                .uri(ub -> ub.path("/users/" + savedUser.getId()).build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("admin" + ":" + "admin").getBytes(StandardCharsets.UTF_8)))
                .exchange()
                .expectStatus()
                .isOk();

        List<User> users = userRepository.findAll();
        assertFalse(CollectionUtils.isEmpty(users));
        assertEquals(4, users.size());
        assertEquals(EMAIL_2, users.get(2).getEmail());
    }
}
