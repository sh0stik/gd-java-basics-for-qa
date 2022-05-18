package com.gd.training_center.model;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.gd.training_center.utils.TimeCalculations.isWeekend;

@Data
public class Student {
    private final String firstName;
    private final String lastName;
    private final String fullName;
    private final Curriculum curriculum;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Student(String firstName, String lastName, LocalDateTime startDate, Curriculum curriculum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = lastName + " " + firstName;
        this.curriculum = curriculum;
        this.startDate = startDate;
        this.endDate = calculateEndDate();
    }

    private LocalDateTime calculateEndDate() {
        LocalDateTime currentDate = startDate.plusDays(0);
        Duration duration = curriculum.calculateDuration();
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
}
