package com.devcors.javaacademy.lesson6.data.repository;

import com.devcors.javaacademy.lesson6.data.entity.BorrowedCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowedCarRepository extends JpaRepository<BorrowedCar, Integer> {

    boolean existsByCarId(Integer carId);
    Optional<BorrowedCar> findByUserIdAndCarId(Integer userId, Integer carId);
    List<BorrowedCar> findBorrowedCarsByUserId(Integer userId);
}
