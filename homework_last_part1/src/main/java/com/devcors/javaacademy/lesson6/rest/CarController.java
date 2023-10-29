package com.devcors.javaacademy.lesson6.rest;

import com.devcors.javaacademy.lesson6.data.dto.CarDTO;
import com.devcors.javaacademy.lesson6.data.entity.Car;
import com.devcors.javaacademy.lesson6.data.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    private final RestTemplate restTemplate;

    @PostMapping("/cars")
    public ResponseEntity<String> addCar(@RequestBody CarDTO carDTO) {
        carService.addCar(carDTO);

        return ResponseEntity.ok("Car added");
    }


    @PutMapping("/cars/{id}")
    public ResponseEntity<String> updateCar(@PathVariable("id") Integer id, @RequestBody CarDTO carDTO) {
        boolean result = carService.updateCar(id, carDTO);

        if (result) {
            return ResponseEntity.ok("Car updated");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("cars/filter")
    public ResponseEntity<List<Car>> getCarsByBrand(@RequestParam("brandName") String brandName) {
        List<Car> cars = carService.getCarsByBrand(brandName);

        return ResponseEntity.ok(cars);
    }


    @DeleteMapping("/cars/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable("id") Integer id) {
        boolean result = carService.deleteCar(id);

        if (result) {
            return ResponseEntity.ok("Car deleted");
        } else
            return ResponseEntity.badRequest().build();
    }


    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars() {
        List<Car> cars = carService.getAllCars();

        return ResponseEntity.ok(cars);
    }


    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable("id") Integer id) {
        Optional<CarDTO> optionalCarDto = carService.getSpecificCar(id);

        return optionalCarDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @GetMapping("/cars/{id}/gps")
    public ResponseEntity<List> getCarGpsLocations(@PathVariable Integer id) {
        ResponseEntity<List> entity = restTemplate.getForEntity("/gps/{id}", List.class, id);

        return ResponseEntity.ok(entity.getBody());
    }
}
