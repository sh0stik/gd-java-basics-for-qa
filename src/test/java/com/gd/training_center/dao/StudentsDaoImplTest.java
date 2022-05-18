package com.gd.training_center.dao;

import com.gd.training_center.dao.impl.StudentsDaoImpl;
import com.gd.training_center.model.Course;
import com.gd.training_center.model.Curriculum;
import com.gd.training_center.model.Student;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.testng.Assert.assertTrue;

public class StudentsDaoImplTest {

    @Test
    public void getAllStudents_twoStudents_returnsListWithSameStudents() {
        Curriculum ignoredCurriculum = new Curriculum("IGNORED", singletonList(new Course("IGNORED", Duration.ofHours(1))));
        Student studentIvanov = new Student("Ivan", "Ivanov", LocalDateTime.of(2022, 4, 8, 10, 0), ignoredCurriculum);
        Student studentPetrov = new Student("Peter", "Petrov", LocalDateTime.of(2022, 5, 6, 10, 0),
                ignoredCurriculum);
        StudentDao studentDao = new StudentsDaoImpl(Arrays.asList(studentIvanov, studentPetrov));
        List<Student> expectedList = Arrays.asList(studentIvanov, studentPetrov);

        List<Student> result = studentDao.getAll();

        assertTrue(result.containsAll(expectedList));
    }

    @Test
    public void getAllStudents_noStudents_returnsEmptyList() {
        StudentDao studentDao = new StudentsDaoImpl(new ArrayList<>());

        List<Student> result = studentDao.getAll();

        assertTrue(result.isEmpty());
    }

}