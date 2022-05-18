package com.gd.training_center.dao;

import com.gd.training_center.dao.impl.CourseDaoImpl;
import com.gd.training_center.model.Course;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class CourseDaoImplTest {


    @Test
    public void getByName_twoCourses_returnsWithMatchingName() {
        Course javaCourse = new Course("Java", Duration.ofHours(16));
        Course jdbcCourse = new Course("JDBC", Duration.ofHours(24));
        CourseDao courseDao = new CourseDaoImpl(Stream.of(javaCourse, jdbcCourse)
                .collect(toMap(Course::getName, identity())));

        Course result = courseDao.getByName("Java");

        assertEquals(result, javaCourse);
    }

    @Test
    public void getByName_twoCourses_returnsNullForNonExistentName() {
        CourseDao courseDao = new CourseDaoImpl(Stream.of(new Course("Java", Duration.ofHours(16)),
                        new Course("JDBC", Duration.ofHours(24)))
                .collect(toMap(Course::getName, identity())));

        Course result = courseDao.getByName("Non existent");

        assertNull(result);
    }
}