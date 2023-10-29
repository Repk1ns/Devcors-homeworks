package com.devcors.javaacademy.lesson6.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
public class BorrowedCar {

    @Id
    @GeneratedValue
    public Integer id;

    @Column
    public Integer userId;

    @Column
    public Integer carId;
}
