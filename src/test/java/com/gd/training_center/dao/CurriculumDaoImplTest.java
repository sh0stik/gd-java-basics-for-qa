package com.gd.training_center.dao;

import com.gd.training_center.dao.impl.CurriculumDaoImpl;
import com.gd.training_center.model.Course;
import com.gd.training_center.model.Curriculum;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class CurriculumDaoImplTest {
    @Mock
    private CourseDao courseDao;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getByName_twoCurriculums_returnsWithMatchingName() {
        Course javaCourse = new Course("Java", Duration.ofHours(16));
        Course jdbcCourse = new Course("JDBC", Duration.ofHours(10));
        Course springCourse = new Course("Spring", Duration.ofHours(10));
        when(courseDao.getByName("Java")).thenReturn(javaCourse);
        when(courseDao.getByName("JDBC")).thenReturn(jdbcCourse);
        when(courseDao.getByName("Spring")).thenReturn(springCourse);
        Curriculum expectedCurriculum = new Curriculum("Java Developer", Arrays.asList(javaCourse, jdbcCourse, springCourse));
        CurriculumDao curriculumDao = new CurriculumDaoImpl(Stream.of(
                        expectedCurriculum,
                        new Curriculum("AQE", singletonList(new Course("IGNORED", Duration.ofHours(1)))))
                .collect(toMap(Curriculum::getName, identity())));

        Curriculum result = curriculumDao.getByName("Java Developer");

        assertEquals(result, expectedCurriculum);
    }

    @Test
    public void getByName_twoCurriculums_returnsNullForNonExistentName() {
        Course javaCourse = new Course("Java", Duration.ofHours(16));
        Course jdbcCourse = new Course("JDBC", Duration.ofHours(10));
        Course springCourse = new Course("Spring", Duration.ofHours(10));
        when(courseDao.getByName("Java")).thenReturn(javaCourse);
        when(courseDao.getByName("JDBC")).thenReturn(jdbcCourse);
        when(courseDao.getByName("Spring")).thenReturn(springCourse);
        CurriculumDao curriculumDao = new CurriculumDaoImpl(Stream.of(
                        new Curriculum("Java Developer", Arrays.asList(javaCourse, jdbcCourse, springCourse)),
                        new Curriculum("AQE", singletonList(new Course("IGNORED", Duration.ofHours(1)))))
                .collect(toMap(Curriculum::getName, identity())));

        Curriculum result = curriculumDao.getByName("Non existent");

        assertNull(result);
    }
}