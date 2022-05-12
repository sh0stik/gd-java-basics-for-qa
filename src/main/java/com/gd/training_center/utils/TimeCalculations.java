package com.gd.training_center.utils;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

public final class TimeCalculations {
    public static LocalDateTime getEndDate(LocalDateTime startDate, Duration duration) {
        LocalDateTime currentDate = startDate.plusDays(0);
        while (!duration.isNegative()) {
            if (isWeekend(currentDate.plusDays(1))) {
                currentDate = currentDate.plusDays(2);
            } else {
                duration = duration.minusHours(8);
                currentDate = currentDate.plusDays(1);

                if (duration.toHours() <= 8) {
                    currentDate = currentDate.plusHours(duration.toHours());
                    break;
                }
            }
        }
        return currentDate;
    }

    private static boolean isWeekend(LocalDateTime date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public static Duration getDurationBetweenTwoDates(LocalDateTime startDate, LocalDateTime endDate) {
        return Duration.between(startDate, endDate);
    }

    private TimeCalculations() {}
}
