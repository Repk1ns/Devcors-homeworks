package com.devcors.javaacademy.lesson2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConditionsTest {

    Conditions conditions;

    @BeforeEach
    void setUp() {
        conditions = new Conditions();
    }

    @Test
    void isGreaterThanTest() {
        assertTrue(conditions.isGreaterThan(5,3));
        assertFalse(conditions.isGreaterThan(1,9));
    }

    @Test
    void compareTest() {
        assertEquals(1, conditions.compare(5, 1));
        assertEquals(-1, conditions.compare(1, 5));
        assertEquals(0, conditions.compare(5, 5));
    }

    @Test
    void isWorkingDayTest() {
        assertTrue(conditions.isWorkingDay("Monday"));
        assertTrue(conditions.isWorkingDay(Constants.THURSDAY));
        assertFalse(conditions.isWorkingDay("Sunday"));
        assertFalse(conditions.isWorkingDay(Constants.SATURDAY));
    }

    @Test
    void dayOfWeekUsingIfTest() {
        assertEquals(1, conditions.dayOfWeekUsingIf(Constants.MONDAY));
        assertEquals(3, conditions.dayOfWeekUsingIf(Constants.WEDNESDAY));
        assertEquals(-1, conditions.dayOfWeekUsingIf("AnotherDay"));
    }

    @Test
    void dayOfWeekUsingSwitch() {
        assertEquals(2, conditions.dayOfWeekUsingSwitch(Constants.TUESDAY));
        assertEquals(7, conditions.dayOfWeekUsingSwitch(Constants.SUNDAY));
        assertEquals(-1, conditions.dayOfWeekUsingSwitch("AnotherDay"));
    }
}
