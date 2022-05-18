package com.gd.training_center.service;

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

import static com.gd.training_center.model.ReportType.FULL;
import static com.gd.training_center.model.ReportType.SHORT;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ReportServiceTest {

    @Mock
    private CurriculumDao curriculumDao;
    @Mock
    private StudentDao studentDao;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void generateShortReport_twoStudentsOneCurriculumTwoCourses_shouldNotContainCoursesInfo() {
        String curriculumName = "JavaDev";
        Course javaCourse = new Course("Java", Duration.ofHours(16));
        Course jdbcCourse = new Course("JDBC", Duration.ofHours(10));
        Curriculum javaDevCurriculum = new Curriculum(curriculumName,
                Arrays.asList(javaCourse, jdbcCourse));
        when(curriculumDao.getByName(curriculumName)).thenReturn(javaDevCurriculum);
        when(studentDao.getAll()).thenReturn(singletonList(new Student("Petrov", "Peter",
                LocalDateTime.of(2022, 5, 11, 10, 0), javaDevCurriculum)));
        ReportService reportService = new ReportServiceImpl(studentDao);

        String result = reportService.generateReport(SHORT);

        assertFalse(result.contains("Course"), "Report should not contain courses information");
    }

    @Test
    public void generateFullReport_twoStudentsOneCurriculumTwoCourses_shouldContainCourseNames() {
        Course javaCourse = new Course("Java", Duration.ofHours(16));
        Course jdbcCourse = new Course("JDBC", Duration.ofHours(10));
        Curriculum javaDevCurriculum = new Curriculum("JavaDev",
                Arrays.asList(javaCourse, jdbcCourse));
        when(curriculumDao.getByName("JavaDev")).thenReturn(javaDevCurriculum);
        when(studentDao.getAll()).thenReturn(singletonList(new Student("Peter", "Petrov",
                LocalDateTime.of(2022, 5, 11, 10, 0), javaDevCurriculum)));
        ReportService reportService = new ReportServiceImpl(studentDao);
        String courseName = "JDBC";

        String result = reportService.generateReport(FULL);

        assertTrue(result.contains(courseName), "Report should contain JDBC course information");
    }

}