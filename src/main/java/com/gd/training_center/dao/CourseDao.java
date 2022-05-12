package com.gd.training_center.dao;

import com.gd.training_center.model.Course;

public interface CourseDao {
    Course getByName(String name);

}
