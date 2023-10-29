package com.devcors.javaacademy.lesson9.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CarLocation {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer carId;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

}
