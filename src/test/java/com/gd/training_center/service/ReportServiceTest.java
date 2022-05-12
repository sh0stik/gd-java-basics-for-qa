package com.gd.training_center.service;

import com.gd.training_center.dao.CourseDao;
import com.gd.training_center.dao.CurriculumDao;
import com.gd.training_center.dao.StudentDao;
import com.gd.training_center.model.Course;
import com.gd.training_center.model.Curriculum;
import com.gd.training_center.model.Student;
import com.gd.training_center.service.impl.ReportServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class ReportServiceTest {
    @Mock
    CourseDao courseDao;
    @Mock
    CurriculumDao curriculumDao;
    @Mock
    StudentDao studentDao;
    @Mock
    Student student;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void generateShortReport_shouldContainsCorrectCurriculumName() {
        when(curriculumDao.getByName("JavaDev")).thenReturn(new Curriculum("JavaDev", Arrays.asList("Java", "JDBC")));
        when(studentDao.getAll()).thenReturn(singletonList(new Student("Petrov", "Peter", "JavaDev", LocalDateTime.of(2022, 5, 11, 10, 0))));
        ReportService reportService = new ReportServiceImpl(studentDao, courseDao, curriculumDao);
        String expectedCurriculumName = "JavaDev";
        when(curriculumDao.getByName("JavaDev").getDuration()).thenReturn(Duration.ofHours(15));

        String result = reportService.generateShortReport();

        assertThat("Report should contains JavaDev curriculum name", result.contains(expectedCurriculumName));
    }

    @Test
    public void generateFullReport_shouldContainsCorrectCurriculumName() {
        when(courseDao.getByName("Selenium")).thenReturn(new Course("Selenium", Duration.ofHours(5)));
        when(courseDao.getByName("Test design")).thenReturn(new Course("Test design", Duration.ofHours(5)));
        when(curriculumDao.getByName("JavaDev")).thenReturn(new Curriculum("JavaDev", Arrays.asList("Selenium", "Test design")));
        Student student = new Student("Doe", "John", "AQE", LocalDateTime.of(2022, 5, 11, 10, 0));
        when(studentDao.getAll()).thenReturn(singletonList(student));
        ReportService reportService = new ReportServiceImpl(studentDao, courseDao, curriculumDao);
        String expectedCurriculumName = "AQE";
        when(curriculumDao.getByName("AQE").getDuration()).thenReturn(Duration.ofHours(10));
        when(this.student.getCurriculum().getCourseList()).thenReturn(singletonList(new Course("Selenium", Duration.ofHours(5))));
        when(this.student.getCurriculum().getCourseList()).thenReturn(singletonList(new Course("Test design", Duration.ofHours(5))));

        String result = reportService.generateFullReport();

        assertThat("Report should contains AQE curriculum name", result.contains(expectedCurriculumName));
    }

}