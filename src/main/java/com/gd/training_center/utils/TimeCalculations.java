package com.gd.training_center.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public final class TimeCalculations {

    public static boolean isWeekend(LocalDateTime date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private TimeCalculations() {
    }
}
