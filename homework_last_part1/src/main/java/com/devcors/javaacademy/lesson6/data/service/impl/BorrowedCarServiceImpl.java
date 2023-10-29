package com.devcors.javaacademy.lesson6.data.service.impl;

import com.devcors.javaacademy.lesson6.data.entity.BorrowedCar;
import com.devcors.javaacademy.lesson6.data.entity.Car;
import com.devcors.javaacademy.lesson6.data.entity.User;
import com.devcors.javaacademy.lesson6.data.entity.enums.BorrowedCarResult;
import com.devcors.javaacademy.lesson6.data.repository.BorrowedCarRepository;
import com.devcors.javaacademy.lesson6.data.repository.CarRepository;
import com.devcors.javaacademy.lesson6.data.repository.UserRepository;
import com.devcors.javaacademy.lesson6.data.service.BorrowedCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowedCarServiceImpl implements BorrowedCarService {

    private final BorrowedCarRepository borrowedCarRepository;

    private final UserRepository userRepository;

    private final CarRepository carRepository;

    @Override
    public BorrowedCarResult borrowCar(Integer userId, Integer carId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalUser.isPresent() && optionalCar.isPresent()) {
            User user = optionalUser.get();
            Car car = optionalCar.get();

            if (borrowedCarRepository.existsByCarId(carId)) {
                return BorrowedCarResult.ALREADY_BORROWED;
            } else {
                BorrowedCar borrowedCar = new BorrowedCar();
                borrowedCar.setUserId(user.getId());
                borrowedCar.setCarId(car.getId());

                borrowedCarRepository.save(borrowedCar);

                return BorrowedCarResult.SUCCESS;
            }
        } else {

            return BorrowedCarResult.NOT_FOUND;
        }
    }


    public BorrowedCarResult returnCar(Integer userId, Integer carId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalUser.isPresent() && optionalCar.isPresent()) {
            User user = optionalUser.get();
            Car car = optionalCar.get();

            Optional<BorrowedCar> optionalBorrowedCar = borrowedCarRepository.findByUserIdAndCarId(user.getId(), car.getId());
            if (optionalBorrowedCar.isPresent()) {
                borrowedCarRepository.delete(optionalBorrowedCar.get());

                return BorrowedCarResult.SUCCESS;
            } else {

                return BorrowedCarResult.NOT_BORROWED;
            }
        } else {
            return BorrowedCarResult.NOT_FOUND;
        }
    }


    public List<Car> getBorrowedCars(Integer userId) {
        List<BorrowedCar> borrowedCars = borrowedCarRepository.findBorrowedCarsByUserId(userId);

        List<Integer> carIds = borrowedCars.stream()
                .map(BorrowedCar::getCarId)
                .toList();

        return carRepository.findAllById(carIds);
    }
}
