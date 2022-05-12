package com.gd.training_center.dao;

import com.gd.training_center.dao.impl.StudentsDaoImpl;
import com.gd.training_center.model.Curriculum;
import com.gd.training_center.model.Student;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class StudentsDaoTest {
    @Mock
    private CurriculumDao curriculumDao;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllStudents_listSize() {
        StudentDao studentDao = new StudentsDaoImpl(Arrays.asList(
                new Student("Ivan", "Ivanov", "Java Developer", LocalDateTime.of(2022, 4, 8, 10, 0)),
                new Student("Petrov", "Peter", "AQE", LocalDateTime.of(2022, 5, 6, 10, 0))),
                curriculumDao);

        int expectedSize = 2;

        int result = studentDao.getAll().size();

        assertEquals(result, expectedSize);
    }

    @Test
    public void getAllStudents_getCurriculum_curriculumName() {
        when(curriculumDao.getByName("Java Developer")).thenReturn(new Curriculum("Java Developer", Arrays.asList("Java", "JDBC", "Spring")));
        StudentDao studentDao = new StudentsDaoImpl(singletonList(
                new Student("Ivan", "Ivanov", "Java Developer", LocalDateTime.of(2022, 4, 8, 10, 0))), curriculumDao);
        String expectedCurriculumName = "Java Developer";

        String result = studentDao.getAll().get(0).getCurriculum().getName();

        assertEquals(result, expectedCurriculumName);
    }

}