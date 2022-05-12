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
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class CurriculumDaoTest {
    @Mock
    private CourseDao courseDao;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getByName_javaDeveloperCourseListSize_3() {
        when(courseDao.getByName("Java")).thenReturn(new Course("Java", Duration.ofHours(16)));
        when(courseDao.getByName("JDBC")).thenReturn(new Course("JDBC", Duration.ofHours(10)));
        when(courseDao.getByName("Spring")).thenReturn(new Course("Spring", Duration.ofHours(10)));

        CurriculumDao curriculumDao = new CurriculumDaoImpl(Stream.of(
                        new Curriculum("Java Developer", Arrays.asList("Java", "JDBC", "Spring")),
                        new Curriculum("AQE", Arrays.asList("Test design", "Page Object", "Selenium")))
                .collect(toMap(Curriculum::getName, identity())), courseDao);

        int expectedListSize = 3;

        int result = curriculumDao.getByName("Java Developer").getCourseList().size();

        assertEquals(result, expectedListSize);
    }

    @Test
    public void getByName_javaDeveloperCurriculumName_javaDeveloper() {
        when(courseDao.getByName("Java")).thenReturn(new Course("Java", Duration.ofHours(16)));
        when(courseDao.getByName("JDBC")).thenReturn(new Course("JDBC", Duration.ofHours(10)));
        when(courseDao.getByName("Spring")).thenReturn(new Course("Spring", Duration.ofHours(10)));

        CurriculumDao curriculumDao = new CurriculumDaoImpl(Stream.of(
                        new Curriculum("Java Developer", Arrays.asList("Java", "JDBC", "Spring")),
                        new Curriculum("AQE", Arrays.asList("Test design", "Page Object", "Selenium")))
                .collect(toMap(Curriculum::getName, identity())), courseDao);
        String curriculumName = "Java Developer";

        String result = curriculumDao.getByName(curriculumName).getName();

        assertEquals(result, curriculumName);
    }

    @Test
    public void getDuration_javaDeveloperCurriculumName_durationInHours() {
        when(courseDao.getByName("Java")).thenReturn(new Course("Java", Duration.ofHours(16)));
        when(courseDao.getByName("JDBC")).thenReturn(new Course("JDBC", Duration.ofHours(10)));
        when(courseDao.getByName("Spring")).thenReturn(new Course("Spring", Duration.ofHours(10)));

        CurriculumDao curriculumDao = new CurriculumDaoImpl(Stream.of(
                        new Curriculum("Java Developer", Arrays.asList("Java", "JDBC", "Spring")),
                        new Curriculum("AQE", Arrays.asList("Test design", "Page Object", "Selenium")))
                .collect(toMap(Curriculum::getName, identity())), courseDao);

        long expectedDuration = 36;

        long result = curriculumDao.getByName("Java Developer").getDuration().toHours();

        assertEquals(result, expectedDuration);
    }

}