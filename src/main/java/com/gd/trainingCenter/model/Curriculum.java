package com.gd.trainingCenter.model;

import lombok.Data;

import java.util.List;


public @Data class Curriculum {
    private final String name;
    private final List<String> courseNames;
    private List<Course> courseList;

    public Curriculum(String name, List<String> courseNames) {
        this.name = name;
        this.courseNames = courseNames;
    }

    public int getDuration() {
        return courseList.stream().mapToInt(Course::getDuration).sum();
    }
}
