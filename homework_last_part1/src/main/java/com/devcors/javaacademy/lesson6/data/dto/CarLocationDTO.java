package com.devcors.javaacademy.lesson6.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarLocationDTO {

    private Integer carId;
    private Double latitude;
    private Double longitude;

}
