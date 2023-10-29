package com.devcors.javaacademy.lesson5.facade;

import org.springframework.stereotype.Service;

@Service
public interface OutputFacade {

    String outputText();

    String outputNumber();
}
