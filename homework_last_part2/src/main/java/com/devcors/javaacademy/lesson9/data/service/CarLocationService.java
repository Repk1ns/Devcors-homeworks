package com.devcors.javaacademy.lesson9.data.service;

import com.devcors.javaacademy.lesson9.data.dto.CarLocationDTO;

import java.util.List;

public interface CarLocationService {
    List<CarLocationDTO> getAllCarLocations(Integer carId);
}
