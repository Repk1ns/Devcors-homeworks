package com.devcors.javaacademy.lesson6.rest;

import com.devcors.javaacademy.lesson6.data.entity.Car;
import com.devcors.javaacademy.lesson6.data.entity.enums.BorrowedCarResult;
import com.devcors.javaacademy.lesson6.data.service.BorrowedCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BorrowedCarController {

    private final BorrowedCarService borrowedCarService;

    @PutMapping("/users/{userId}/car/borrow/{carId}")
    public ResponseEntity<String> borrowCar(@PathVariable("userId") Integer userId, @PathVariable("carId") Integer carId) {
            BorrowedCarResult result = borrowedCarService.borrowCar(userId, carId);

        switch (result) {
            case SUCCESS:
                return ResponseEntity.ok("Car successful borrowed");
            case ALREADY_BORROWED:
                return ResponseEntity.badRequest().body("Car is already borrowed");
            case NOT_FOUND:
                return ResponseEntity.badRequest().body("User or car not found");
            default:
                return ResponseEntity.badRequest().body("Error occurred");
        }
    }

    @PutMapping("/users/{userId}/car/return/{carId}")
    public ResponseEntity<String> returnCar(@PathVariable("userId") Integer userId, @PathVariable("carId") Integer carId) {

            BorrowedCarResult result = borrowedCarService.returnCar(userId, carId);

            switch (result) {
                case SUCCESS:
                    return ResponseEntity.ok("Car successful returned");
                case NOT_BORROWED:
                    return ResponseEntity.badRequest().body("Car is not borrowed by this user");
                case NOT_FOUND:
                    return ResponseEntity.badRequest().body("User or car not found");
                default:
                    return ResponseEntity.badRequest().body("Error occurred");
            }
    }


    @GetMapping("/users/{userId}/car")
    public ResponseEntity<List<Car>> getBorrowedCars(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(borrowedCarService.getBorrowedCars(userId));
    }
}
