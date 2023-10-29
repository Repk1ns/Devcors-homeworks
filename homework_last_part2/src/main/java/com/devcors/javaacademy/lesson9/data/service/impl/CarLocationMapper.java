package com.devcors.javaacademy.lesson9.data.service.impl;

import com.devcors.javaacademy.lesson9.data.dto.CarLocationDTO;
import com.devcors.javaacademy.lesson9.data.entity.CarLocation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarLocationMapper {

    public CarLocationDTO EntityToDto(CarLocation carLocation) {
        return CarLocationDTO.builder()
                .carId(carLocation.getCarId())
                .latitude(carLocation.getLatitude())
                .longitude(carLocation.getLongitude())
                .build();
    }

    public List<CarLocationDTO> convertToDtoList(List<CarLocation> carLocationList) {
        return carLocationList.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }
}
