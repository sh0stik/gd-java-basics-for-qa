package com.gd.training_center.dao.impl;

import com.gd.training_center.dao.CourseDao;
import com.gd.training_center.dao.CurriculumDao;
import com.gd.training_center.model.Curriculum;

import java.util.Map;
import java.util.stream.Collectors;

public class CurriculumDaoImpl implements CurriculumDao {
    private final Map<String, Curriculum> curriculumByName;
    CourseDao courseDao;

    public CurriculumDaoImpl(Map<String, Curriculum> curriculumByName, CourseDao courseDao) {
        this.curriculumByName = curriculumByName;
        this.courseDao = courseDao;
        setCourseList();
    }

    @Override
    public Curriculum getByName(String name) {
        return curriculumByName.get(name);
    }


    private void setCourseList() {
        curriculumByName.keySet().forEach(name ->
                curriculumByName.get(name).setCourseList(curriculumByName.get(name).getCourseNames()
                        .stream().map(courseDao::getByName).collect(Collectors.toList())));
    }
}
