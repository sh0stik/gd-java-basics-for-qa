package com.gd.training_center;

import com.gd.training_center.utils.TimeCalculations;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TimeCalculationsTest {

    @Test
    public void isWeekend_monday_returnsFalse() {
        LocalDateTime startDate = LocalDateTime.of(2022, 5, 9, 10, 0);

        boolean result = TimeCalculations.isWeekend(startDate);

        assertFalse(result, "May 9th 2022 is Monday");
    }

    @Test
    public void isWeekend_saturday_returnsTrue() {
        LocalDateTime startDate = LocalDateTime.of(2022, 5, 14, 10, 0);

        boolean result = TimeCalculations.isWeekend(startDate);

        assertTrue(result, "May 14th 2022 is Saturday");
    }
}