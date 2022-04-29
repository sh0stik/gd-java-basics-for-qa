package com.gd.trainingCenter.model;

import lombok.Data;

import java.time.LocalDateTime;

public @Data class Student {
    private final String firstName;
    private final String lastName;
    private final String curriculumName;
    private final LocalDateTime startDate;
    private Curriculum curriculum;

    public Student(String firstName, String lastName, String curriculumName, LocalDateTime startDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.curriculumName = curriculumName;
        this.startDate = startDate;
    }
}
