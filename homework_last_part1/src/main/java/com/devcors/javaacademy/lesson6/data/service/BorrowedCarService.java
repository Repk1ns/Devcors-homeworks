package com.devcors.javaacademy.lesson6.data.service;

import com.devcors.javaacademy.lesson6.data.entity.Car;
import com.devcors.javaacademy.lesson6.data.entity.enums.BorrowedCarResult;

import java.util.List;

public interface BorrowedCarService {
    BorrowedCarResult borrowCar(Integer userId, Integer carId);
    BorrowedCarResult returnCar(Integer userId, Integer carId);
    List<Car> getBorrowedCars(Integer userId);
}
