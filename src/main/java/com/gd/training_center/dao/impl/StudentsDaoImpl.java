package com.gd.training_center.dao.impl;

import com.gd.training_center.dao.StudentDao;
import com.gd.training_center.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsDaoImpl implements StudentDao {
    private final List<Student> students;

    public StudentsDaoImpl(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    @Override
    public List<Student> getAll() {
        return students;
    }
}
