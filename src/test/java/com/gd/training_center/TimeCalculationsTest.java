package com.gd.training_center;

import com.gd.training_center.utils.TimeCalculations;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

public class TimeCalculationsTest {

    @Test
    public void getEndDate_startOnMonday16Hours_tuesdayEndOfTheDay() {
        LocalDateTime startDate = LocalDateTime.of(2022, 5, 9, 10, 0);
        Duration duration = Duration.ofHours(16);
        LocalDateTime expectedEndDate = LocalDateTime.of(2022, 5, 10, 18, 0);

        LocalDateTime result = TimeCalculations.getEndDate(startDate, duration);

        assertEquals(result, expectedEndDate);
    }

    @Test
    public void getEndDate_startOnMonday10Hours_tuesdayNoon() {
        LocalDateTime startDate = LocalDateTime.of(2022, 5, 9, 10, 0);
        Duration duration = Duration.ofHours(10);
        LocalDateTime expectedEndDate = LocalDateTime.of(2022, 5, 10, 12, 0);

        LocalDateTime result = TimeCalculations.getEndDate(startDate, duration);

        assertEquals(result, expectedEndDate);
    }

    @Test
    public void getEndDate_startOnFriday10Hours_MondayNoon() {
        LocalDateTime startDate = LocalDateTime.of(2022, 5, 13, 10, 0);
        Duration duration = Duration.ofHours(10);
        LocalDateTime expectedEndDate = LocalDateTime.of(2022, 5, 16, 12, 0);

        LocalDateTime result = TimeCalculations.getEndDate(startDate, duration);

        assertEquals(result, expectedEndDate);
    }

    @Test
    public void getEndDate_startOnThursday16Hours_fridayEndOfTheDay() {
        LocalDateTime startDate = LocalDateTime.of(2022, 5, 12, 10, 0);
        Duration duration = Duration.ofHours(16);
        LocalDateTime expectedEndDate = LocalDateTime.of(2022, 5, 13, 18, 0);

        LocalDateTime result = TimeCalculations.getEndDate(startDate, duration);

        assertEquals(result, expectedEndDate);
    }

    @Test
    public void getDurationBetweenTwoDates_startDateMay9EndDateMay13_4Days() {
        LocalDateTime startDate = LocalDateTime.of(2022, 5, 9, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 5, 13, 18, 0);
        long expectedDuration = Duration.ofDays(4).toDays();

        long result = TimeCalculations.getDurationBetweenTwoDates(startDate, endDate).toDays();

        assertEquals(result, expectedDuration);
    }

    @Test
    public void calculateDifferenceInHours_startDateMay9EndDateMay13_104Hours() {
        LocalDateTime startDate = LocalDateTime.of(2022, 5, 9, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 5, 13, 18, 0);
        long expectedDuration = Duration.ofHours(104).toHours();

        long result = TimeCalculations.getDurationBetweenTwoDates(startDate, endDate).toHours();

        assertEquals(result, expectedDuration);
    }
}