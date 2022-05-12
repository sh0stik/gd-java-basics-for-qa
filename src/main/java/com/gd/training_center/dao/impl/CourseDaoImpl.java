package com.gd.training_center.dao.impl;

import com.gd.training_center.dao.CourseDao;
import com.gd.training_center.model.Course;

import java.util.Map;

public class CourseDaoImpl implements CourseDao {
    private final Map<String, Course> coursesByName;

    public CourseDaoImpl(Map<String,Course> coursesByName) {
        this.coursesByName = coursesByName;
    }

    @Override
    public Course getByName(String name) {
        return coursesByName.get(name);
    }
}
