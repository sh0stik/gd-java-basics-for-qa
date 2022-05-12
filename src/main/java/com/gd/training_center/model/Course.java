package com.gd.training_center.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;

@AllArgsConstructor
public @Data class Course {
    /**
     * Course is unique
     */
    private final String name;
    private final Duration duration;
}
