package com.gd.training_center.model;

import lombok.Data;

import java.time.Duration;
import java.util.List;

@Data
public class Curriculum {
    private final String name;
    private List<Course> courseList;
    private Duration duration;

    public Curriculum(String name, List<Course> courseList) {
        this.name = name;
        this.courseList = courseList;
        this.duration = calculateDuration();
    }

    public Duration calculateDuration() {
        Duration calculatedDuration = Duration.ofHours(0);
        for (Course course : courseList) {
            calculatedDuration = calculatedDuration.plusHours(course.getDuration().toHours());
        }
        return calculatedDuration;
    }
}
