package com.devcors.javaacademy.lesson5;

import com.devcors.javaacademy.lesson5.configuration.TextConfiguration;
import com.devcors.javaacademy.lesson5.facade.OutputFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EnableConfigurationProperties(TextConfiguration.class)
class Lesson5Test {

    @Autowired
    private OutputFacade outputFacade;

    @Test
    void outputText() {
        assertEquals("Spring boot is awesome and Quarkus sucks", outputFacade.outputText());
    }

    @Test
    void outputNumber() {
        assertEquals("5", outputFacade.outputNumber());
    }
}