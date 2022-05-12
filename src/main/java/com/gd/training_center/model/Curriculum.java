package com.gd.training_center.model;

import lombok.Data;

import java.time.Duration;
import java.util.List;


public @Data class Curriculum {
    private final String name;
    private final List<String> courseNames;
    private List<Course> courseList;

    public Curriculum(String name, List<String> courseNames) {
        this.name = name;
        this.courseNames = courseNames;
    }

    public Duration getDuration() {
        Duration duration = Duration.ofHours(0);
        for (Course course : courseList) {
            duration = duration.plusHours(course.getDuration().toHours());
        }
        return duration;
    }
}
