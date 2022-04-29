package com.gd.trainingCenter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class Course {
    /**
     * Course is unique
     */
    private final String name;
    private final int duration;
}
