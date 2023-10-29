package com.devcors.javaacademy.lesson6.data.service.impl;

import com.devcors.javaacademy.lesson6.data.dto.CarDTO;
import com.devcors.javaacademy.lesson6.data.entity.Car;
import com.devcors.javaacademy.lesson6.data.service.EntityMapper;
import org.springframework.stereotype.Service;

@Service
public class CarMapper implements EntityMapper<Car, CarDTO> {


    public Car DtoToEntity(CarDTO carDTO, Car car) {
        if (car == null) {
            car = new Car();
        }

        car.setYear(carDTO.getYear());
        car.setBrand(carDTO.getBrand());
        car.setLicencePlate(carDTO.getLicencePlate());
        car.setType(carDTO.getType());
        car.setColor(carDTO.getColor());

        return car;
    }


    public CarDTO EntityToDto(Car car) {
        return CarDTO.builder()
                .year(car.getYear())
                .brand(car.getBrand())
                .licencePlate(car.getLicencePlate())
                .type(car.getType())
                .color(car.getColor())
                .build();
    }
}
