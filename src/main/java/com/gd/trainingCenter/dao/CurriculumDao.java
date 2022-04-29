package com.gd.trainingCenter.dao;

import com.gd.trainingCenter.model.Curriculum;

public interface CurriculumDao {
    Curriculum getByName(String name);
    int getDuration(String name);
}
