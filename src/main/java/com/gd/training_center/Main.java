package com.gd.training_center;

import com.gd.training_center.dao.CourseDao;
import com.gd.training_center.dao.CurriculumDao;
import com.gd.training_center.dao.StudentDao;
import com.gd.training_center.dao.impl.CourseDaoImpl;
import com.gd.training_center.dao.impl.CurriculumDaoImpl;
import com.gd.training_center.dao.impl.StudentsDaoImpl;
import com.gd.training_center.model.Course;
import com.gd.training_center.model.Curriculum;
import com.gd.training_center.model.Student;
import com.gd.training_center.service.ReportService;
import com.gd.training_center.service.impl.ReportServiceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Main {
    public static void main(String[] args) {
        CourseDao courseDao = new CourseDaoImpl(Stream.of(
                new Course("Java", Duration.ofHours(16)),
                new Course("JDBC", Duration.ofHours(24)),
                new Course("Spring", Duration.ofHours(16)),
                new Course("Test design", Duration.ofHours(10)),
                new Course("Page Object", Duration.ofHours(16)),
                new Course("Selenium", Duration.ofHours(16))
        ).collect(toMap(Course::getName, identity())));
        CurriculumDao curriculumDao = new CurriculumDaoImpl(Stream.of(
                        new Curriculum("Java Developer", Arrays.asList("Java", "JDBC", "Spring")),
                        new Curriculum("AQE", Arrays.asList("Test design", "Page Object", "Selenium")))
                .collect(toMap(Curriculum::getName, identity())), courseDao);
        StudentDao studentDao = new StudentsDaoImpl(Arrays.asList(
                new Student("Ivan", "Ivanov", "Java Developer", LocalDateTime.of(2022, 4, 8, 10, 0)),
                new Student("Ivan", "Sidorov", "AQE", LocalDateTime.of(2022, 5, 5, 10, 0))), curriculumDao);
        ReportService reportService = new ReportServiceImpl(studentDao, courseDao, curriculumDao);

        Integer parameter = getInputParameter();

        printReport(reportService, parameter);
    }

    private static void printReport(ReportService reportService, Integer parameter) {
        if (isaShortReport(parameter)) {
            System.out.printf("Short (Generating report date - %s):%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm")));
            System.out.println(reportService.generateShortReport());
        } else {
            System.out.printf("Full (Generating report date - %s):%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm")));
            System.out.println(reportService.generateFullReport());
        }
    }

    private static Integer getInputParameter() {
        Scanner input = new Scanner(System.in);
        System.out.println("Input 0 for Short report, any digit for Full report");
        return input.nextInt();
    }

    private static boolean isaShortReport(Integer parameter) {
        return parameter == null || parameter == 0;
    }
}
