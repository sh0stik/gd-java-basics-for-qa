package com.gd.training_center.dao.impl;

import com.gd.training_center.dao.CurriculumDao;
import com.gd.training_center.model.Curriculum;

import java.util.HashMap;
import java.util.Map;

public class CurriculumDaoImpl implements CurriculumDao {
    private final Map<String, Curriculum> curriculumByName;

    public CurriculumDaoImpl(Map<String, Curriculum> curriculumByName) {
        this.curriculumByName = new HashMap<>(curriculumByName);
    }

    @Override
    public Curriculum getByName(String name) {
        return curriculumByName.get(name);
    }

}
