package com.gd.trainingCenter.dao.impl;

import com.gd.trainingCenter.dao.CourseDao;
import com.gd.trainingCenter.model.Course;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class CourseDaoImpl implements CourseDao {
    private final Map<String, Course> coursesByName;

    public CourseDaoImpl() {
        coursesByName = Stream.of(
                new Course("Java", 16),
                new Course("JDBC", 24),
                new Course("Spring", 16),
                new Course("Test design", 10),
                new Course("Page Object", 16),
                new Course("Selenium", 16)
        ).collect(toMap(Course::getName, identity()));
    }

    @Override
    public Course getByName(String name) {
        return coursesByName.get(name);
    }
}
