package com.example.careermanagement.unit;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DateCalculatorTest {

    @Test
    void whenAddingDays_thenDateIsCorrect() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);

        LocalDate result = startDate.plusDays(10);

        assertEquals(LocalDate.of(2025, 1, 11), result);
    }

    @Test
    void whenSubtractingDays_thenDateIsCorrect() {
        LocalDate startDate = LocalDate.of(2025, 1, 10);

        LocalDate result = startDate.minusDays(5);

        assertEquals(LocalDate.of(2025, 1, 5), result);
    }
}
