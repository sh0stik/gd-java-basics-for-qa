package com.gd.trainingCenter.dao.impl;

import com.gd.trainingCenter.dao.StudentDao;
import com.gd.trainingCenter.model.Student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class StudentsDaoImpl implements StudentDao {
    private final List<Student> students;

    public StudentsDaoImpl() {
        students = Arrays.asList(
                new Student("Ivan", "Ivanov", "Java Developer", LocalDateTime.parse("2022-04-08 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))),
                new Student("Ivan", "Sidorov", "AQE", LocalDateTime.parse("2022-04-15 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
        );
    }

    @Override
    public List<Student> getAll() {
        return students;
    }
}
