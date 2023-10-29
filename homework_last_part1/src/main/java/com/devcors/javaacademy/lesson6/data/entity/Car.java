package com.devcors.javaacademy.lesson6.data.entity;

import com.devcors.javaacademy.lesson6.data.entity.enums.CarColor;
import com.devcors.javaacademy.lesson6.data.entity.enums.CarType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "car_year")
    private Short year;

    @Column
    private String brand;

    @Column
    private String licencePlate;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_type")
    private CarType type;

    @Enumerated(EnumType.STRING)
    private CarColor color;

}
