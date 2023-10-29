package com.devcors.javaacademy.lesson2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoopsTest {

    Loops loops;

    @BeforeEach
    void setUp() {
        loops = new Loops();
    }

    @Test
    void sumTest() {
        assertEquals(0, loops.sum(0));
        assertEquals(6, loops.sum(3));
        assertEquals(528, loops.sum(32));
    }

    @Test
    void sumEvenTest() {
        assertEquals(90, loops.sumEven(19));
    }

    @Test
    void hailstoneProblemTest() {
        assertEquals(7, loops.hailstoneProblem(3));
        assertEquals(18, loops.hailstoneProblem(29));
    }
}
