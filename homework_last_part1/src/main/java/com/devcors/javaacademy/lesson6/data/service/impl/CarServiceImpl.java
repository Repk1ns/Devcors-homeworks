package com.devcors.javaacademy.lesson6.data.service.impl;

import com.devcors.javaacademy.lesson6.data.dto.CarDTO;
import com.devcors.javaacademy.lesson6.data.entity.Car;
import com.devcors.javaacademy.lesson6.data.repository.CarRepository;
import com.devcors.javaacademy.lesson6.data.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final CarMapper carMapper;


    public void addCar(CarDTO carDTO) {
        Car car = carMapper.DtoToEntity(carDTO, null);

        carRepository.save(car);
    }


    public boolean updateCar(Integer id, CarDTO carDTO) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            Car car = carMapper.DtoToEntity(carDTO, optionalCar.get());

            carRepository.save(car);

            return true;
        } else {

            return false;
        }
    }


    public boolean deleteCar(Integer id) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isPresent()) {
            carRepository.delete(optionalCar.get());

            return true;
        } else {

            return false;
        }
    }


    public List<Car> getCarsByBrand(String brandName) {
        return carRepository.findByBrand(brandName);
    }


    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    @Override
    public Optional<CarDTO> getSpecificCar(Integer id) {
         Optional<Car> optionalCar = carRepository.findById(id);

          if (optionalCar.isPresent()) {
                CarDTO carDTO = carMapper.EntityToDto(optionalCar.get());

                return Optional.of(carDTO);
          } else {

                return Optional.empty();
          }
    }
}
