package com.gd.training_center.model;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class CurriculumTest {

    @Test
    public void getDuration_twoCourses_returnsDuration() {
        Curriculum curriculum = new Curriculum("Test", Arrays.asList(new Course("Course One",
                Duration.ofHours(10)), new Course("Course Two", Duration.ofHours(8))));
        Duration expectedDuration = Duration.ofHours(18);

        Duration result = curriculum.getDuration();

        assertEquals(result, expectedDuration);
    }

    @Test
    public void getDuration_emptyList_returnsDuration() {
        Curriculum curriculum = new Curriculum("Test", new ArrayList<>());
        Duration expectedDuration = Duration.ofHours(0);

        Duration result = curriculum.getDuration();

        assertEquals(result, expectedDuration);
    }
}