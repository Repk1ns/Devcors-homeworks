package com.devcors.javaacademy.lesson9.rest;

import com.devcors.javaacademy.lesson9.data.dto.CarLocationDTO;
import com.devcors.javaacademy.lesson9.data.entity.CarLocation;
import com.devcors.javaacademy.lesson9.data.service.CarLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GpsController {

    private final CarLocationService carLocationService;


    @GetMapping("/gps/{carId}")
    public ResponseEntity<List<CarLocationDTO>> getGpsLocations(@PathVariable("carId") Integer carId) {
        List<CarLocationDTO> carLocations = carLocationService.getAllCarLocations(carId);

        return ResponseEntity.ok(carLocations);
    }
}
