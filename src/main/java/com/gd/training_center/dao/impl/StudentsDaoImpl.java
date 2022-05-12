package com.gd.training_center.dao.impl;

import com.gd.training_center.dao.CurriculumDao;
import com.gd.training_center.dao.StudentDao;
import com.gd.training_center.model.Student;

import java.util.List;

public class StudentsDaoImpl implements StudentDao {
    private final List<Student> students;
    private final CurriculumDao curriculumDao;

    public StudentsDaoImpl(List<Student> students, CurriculumDao curriculumDao) {
        this.students = students;
        this.curriculumDao = curriculumDao;
        setCurriculum();
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    private void setCurriculum() {
        students.forEach(student -> student.setCurriculum(curriculumDao.getByName(student.getCurriculumName())));
    }
}
