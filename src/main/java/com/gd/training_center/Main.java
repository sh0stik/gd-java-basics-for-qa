package com.gd.training_center;

import com.gd.training_center.dao.CourseDao;
import com.gd.training_center.dao.CurriculumDao;
import com.gd.training_center.dao.StudentDao;
import com.gd.training_center.dao.impl.CourseDaoImpl;
import com.gd.training_center.dao.impl.CurriculumDaoImpl;
import com.gd.training_center.dao.impl.StudentsDaoImpl;
import com.gd.training_center.model.Course;
import com.gd.training_center.model.Curriculum;
import com.gd.training_center.model.ReportType;
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
                        new Curriculum("Java Developer", Arrays.asList(courseDao.getByName("Java"), courseDao.getByName("JDBC"), courseDao.getByName("Spring"))),
                        new Curriculum("AQE", Arrays.asList(courseDao.getByName("Test design"), courseDao.getByName("Page Object"), courseDao.getByName("Selenium"))))
                .collect(toMap(Curriculum::getName, identity())));
        StudentDao studentDao = new StudentsDaoImpl(Arrays.asList(
                new Student("Ivan", "Ivanov", LocalDateTime.of(2022, 5, 25, 10, 0), curriculumDao.getByName(
                        "Java Developer")),
                new Student("Ivan", "Sidorov", LocalDateTime.of(2022, 5, 5, 10, 0), curriculumDao.getByName("AQE"))));

        ReportType reportType;
        Integer parameter = getInputParameter();

        if (parameter == 0) {
            reportType = ReportType.SHORT;
        } else {
            reportType = ReportType.FULL;
        }
        ReportService reportService = new ReportServiceImpl(studentDao);
        printReport(reportService, reportType);
    }

    private static void printReport(ReportService reportService, ReportType reportType) {
        switch (reportType) {
            case SHORT:
                System.out.printf("Short (Generating report date - %s):%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm")));
                break;
            case FULL:
                System.out.printf("Full (Generating report date - %s):%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm")));
                break;
        }
        System.out.println(reportService.generateReport(reportType));
    }

    private static Integer getInputParameter() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 0 for Short report, any digit for Full report");
        return input.nextInt();
    }
}
