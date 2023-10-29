package com.devcors.javaacademy.lesson6.data.service;

import com.devcors.javaacademy.lesson6.data.dto.CarDTO;
import com.devcors.javaacademy.lesson6.data.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> getAllCars();
    Optional<CarDTO> getSpecificCar(Integer id);
    void addCar(CarDTO carDTO);
    boolean updateCar(Integer id, CarDTO carDTO);
    boolean deleteCar(Integer id);
    List<Car> getCarsByBrand(String brandName);
}
