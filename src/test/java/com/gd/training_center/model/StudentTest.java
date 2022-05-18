package com.gd.training_center.model;

import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static java.util.Collections.singletonList;
import static org.testng.Assert.assertEquals;

public class StudentTest {

    @Test
    public void getEndDate_startOnMonday10Hours_tuesdayNoon() {
        Curriculum curriculum = new Curriculum("Curriculum", singletonList(new Course("IGNORED",
                Duration.ofHours(10))));
        Student student = new Student("Ivan", "Ivanov", LocalDateTime.of(2022, 5, 16, 10, 0),
                curriculum);
        LocalDateTime expectedEndDate = LocalDateTime.of(2022, 5, 17, 12, 0);

        LocalDateTime result = student.getEndDate();

        assertEquals(result, expectedEndDate);
    }


    @Test
    public void getEndDate_startOnFriday10Hours_MondayNoon() {
        Curriculum curriculum = new Curriculum("Curriculum", singletonList(new Course("IGNORED",
                Duration.ofHours(10))));
        Student student = new Student("Ivan", "Ivanov", LocalDateTime.of(2022, 5, 13, 10, 0),
                curriculum);
        LocalDateTime expectedEndDate = LocalDateTime.of(2022, 5, 16, 12, 0);

        LocalDateTime result = student.getEndDate();

        assertEquals(result, expectedEndDate);
    }

    @Test
    public void getEndDate_startOnThursday16Hours_fridayEndOfTheDay() {
        Curriculum curriculum = new Curriculum("Curriculum", singletonList(new Course("IGNORED",
                Duration.ofHours(16))));
        Student student = new Student("Ivan", "Ivanov", LocalDateTime.of(2022, 5, 12, 10, 0),
                curriculum);
        LocalDateTime expectedEndDate = LocalDateTime.of(2022, 5, 13, 18, 0);

        LocalDateTime result = student.getEndDate();

        assertEquals(result, expectedEndDate);
    }

    @Test
    public void getEndDate_startOnMonday16Hours_tuesdayEndOfTheDay() {
        Curriculum curriculum = new Curriculum("Curriculum", singletonList(new Course("IGNORED",
                Duration.ofHours(16))));
        Student student = new Student("Ivan", "Ivanov", LocalDateTime.of(2022, 5, 9, 10, 0),
                curriculum);
        LocalDateTime expectedEndDate = LocalDateTime.of(2022, 5, 10, 18, 0);

        LocalDateTime result = student.getEndDate();

        assertEquals(result, expectedEndDate);
    }
}