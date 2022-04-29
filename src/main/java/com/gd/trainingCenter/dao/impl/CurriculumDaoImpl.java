package com.gd.trainingCenter.dao.impl;

import com.gd.trainingCenter.dao.CurriculumDao;
import com.gd.trainingCenter.model.Curriculum;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class CurriculumDaoImpl implements CurriculumDao {
    private final Map<String, Curriculum> curriculumByName;

    public CurriculumDaoImpl() {
        curriculumByName = Stream.of(
                new Curriculum("Java Developer", Arrays.asList("Java", "JDBC", "Spring")),
                new Curriculum("AQE", Arrays.asList("Test design", "Page Object", "Selenium"))
        ).collect(toMap(Curriculum::getName, identity()));
    }

    @Override
    public Curriculum getByName(String name) {
        return curriculumByName.get(name);
    }

    @Override
    public int getDuration(String name) {
        return getByName(name).getDuration();
    }
}
