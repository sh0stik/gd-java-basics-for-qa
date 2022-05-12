package com.gd.training_center.dao;

import com.gd.training_center.model.Curriculum;

public interface CurriculumDao {
    Curriculum getByName(String name);
}
