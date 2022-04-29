package com.gd.trainingCenter.service;

import java.time.LocalDateTime;

public interface TimeCalculationService {
    long calculateTimeSinceStart(LocalDateTime startDate, LocalDateTime currentDate);

    long calculateDifferenceInHours(LocalDateTime startDate, LocalDateTime currentDate);
}
