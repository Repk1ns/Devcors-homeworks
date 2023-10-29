package com.devcors.javaacademy.lesson6;

import com.devcors.javaacademy.lesson6.data.entity.BorrowedCar;
import com.devcors.javaacademy.lesson6.data.entity.Car;
import com.devcors.javaacademy.lesson6.data.entity.enums.CarColor;
import com.devcors.javaacademy.lesson6.data.entity.enums.CarType;
import com.devcors.javaacademy.lesson6.data.repository.BorrowedCarRepository;
import com.devcors.javaacademy.lesson6.data.repository.CarRepository;
import com.devcors.javaacademy.lesson6.data.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Lesson6Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class BorrowedCarControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorrowedCarRepository borrowedCarRepository;


    @BeforeEach
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void beforeEach() {
        webTestClient.post()
                .uri(ub -> ub.path("/cars").build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("admin" + ":" + "admin").getBytes(StandardCharsets.UTF_8)))
                .bodyValue(CAR_1)
                .exchange()
                .expectStatus()
                .isOk();
    }


    @AfterEach
    void afterEach() {
        carRepository.deleteAll();
        borrowedCarRepository.deleteAll();
    }

    private static final String LICENCE_PLATE_1 = "YKJ56";

    private static final String BRAND_1 = "Mercedes";

    private static final short YEAR_1 = (short) 2015;


    private static final Car CAR_1 = Car.builder()
            .brand(BRAND_1)
            .year(YEAR_1)
            .licencePlate(LICENCE_PLATE_1)
            .color(CarColor.BLACK)
            .type(CarType.SEDAN)
            .build();


    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void cantBorrowOrReturnCar() {

        webTestClient.put()
                .uri(ub -> ub.path("/users/42/car/borrow/15").build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "user").getBytes(StandardCharsets.UTF_8)))
                .exchange()
                .expectStatus()
                .isBadRequest();

        webTestClient.put()
                .uri(ub -> ub.path("/users/2/car/return/21").build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "user").getBytes(StandardCharsets.UTF_8)))
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void borrowAndReturnCar() {
        webTestClient.put()
                .uri(ub -> ub.path("/users/1/car/borrow/1").build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "user").getBytes(StandardCharsets.UTF_8)))
                .exchange()
                .expectStatus()
                .isOk();

        Optional<BorrowedCar> borrowedCar = borrowedCarRepository.findByUserIdAndCarId(1, 1);
        assertFalse(borrowedCar.isEmpty());
        assertEquals(1, borrowedCar.get().getUserId());
        assertEquals(1, borrowedCar.get().getCarId());

        webTestClient.put()
                .uri(ub -> ub.path("/users/1/car/return/1").build())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "user").getBytes(StandardCharsets.UTF_8)))
                .exchange()
                .expectStatus()
                .isOk();

        Optional<BorrowedCar> alreadyReturned = borrowedCarRepository.findByUserIdAndCarId(1, 1);
        assertTrue(alreadyReturned.isEmpty());
    }

}
