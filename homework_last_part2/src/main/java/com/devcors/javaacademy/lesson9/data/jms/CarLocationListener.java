package com.devcors.javaacademy.lesson9.data.jms;

import com.devcors.javaacademy.lesson9.data.entity.CarLocation;
import com.devcors.javaacademy.lesson9.data.repository.CarLocationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CarLocationListener {

    private final CarLocationRepository carLocationRepository;
    private final ObjectMapper objectMapper;


    @RabbitListener(queues = "carLocationQueue")
    public void listen(String message) {
        try {
            CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);
            carLocationRepository.save(carLocation);
        } catch(JsonProcessingException e) {
            log.error("Error when creating CarLocation from queue message.", e);
        }
    }
}
