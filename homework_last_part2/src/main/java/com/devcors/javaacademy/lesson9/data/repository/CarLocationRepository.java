package com.devcors.javaacademy.lesson9.data.repository;

import com.devcors.javaacademy.lesson9.data.entity.CarLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarLocationRepository extends JpaRepository<CarLocation, Integer> {
    List<CarLocation> findAllByCarId(Integer carId);
}
