package com.devcors.javaacademy.lesson9.data.service.impl;

import com.devcors.javaacademy.lesson9.data.dto.CarLocationDTO;
import com.devcors.javaacademy.lesson9.data.repository.CarLocationRepository;
import com.devcors.javaacademy.lesson9.data.service.CarLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarLocationServiceImpl implements CarLocationService {

    private final CarLocationRepository carLocationRepository;
    private final CarLocationMapper carLocationMapper;


    public List<CarLocationDTO> getAllCarLocations(Integer carId) {

        return carLocationMapper.convertToDtoList(carLocationRepository.findAllByCarId(carId));
    }
}
