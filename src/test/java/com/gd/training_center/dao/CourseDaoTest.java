package com.gd.training_center.dao;

import com.gd.training_center.dao.impl.CourseDaoImpl;
import com.gd.training_center.model.Course;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.testng.Assert.assertEquals;

public class CourseDaoTest {


    @Test
    public void getByName_javaCourseDuration_16Hours() {
        CourseDao courseDao = new CourseDaoImpl(Stream.of(
                new Course("Java", Duration.ofHours(16)),
                new Course("JDBC", Duration.ofHours(24)),
                new Course("Spring", Duration.ofHours(16))
        ).collect(toMap(Course::getName, identity())));
        String courseName = "Java";
        Duration expectedDuration = Duration.ofHours(16);

        Duration result = courseDao.getByName(courseName).getDuration();

        assertEquals(result, expectedDuration, "Course duration is not as expected");
    }

    @Test
    public void getByName_javaCourseName_Java() {
        CourseDao courseDao = new CourseDaoImpl(Stream.of(
                new Course("Java", Duration.ofHours(16)),
                new Course("JDBC", Duration.ofHours(24)),
                new Course("Spring", Duration.ofHours(16))
        ).collect(toMap(Course::getName, identity())));
        String courseName = "Java";

        String result = courseDao.getByName(courseName).getName();

        assertEquals(result, courseName, "Course name is not as expected");
    }
}