package com.gd.trainingCenter.dao;

import com.gd.trainingCenter.model.Course;

public interface CourseDao {
    Course getByName(String name);

}
