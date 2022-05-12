package com.gd.training_center.service.impl;

import com.gd.training_center.dao.CourseDao;
import com.gd.training_center.dao.CurriculumDao;
import com.gd.training_center.dao.StudentDao;
import com.gd.training_center.model.Course;
import com.gd.training_center.model.Student;
import com.gd.training_center.service.ReportService;
import com.gd.training_center.utils.TimeCalculations;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportServiceImpl implements ReportService {
    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final CurriculumDao curriculumDao;
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now();
    private List<Student> students;
    LocalDateTime endDate;
    Duration durationBetweenEndDateAndCurrentDate;
    long durationBetweenEndHourAndCurrentHour;


    public ReportServiceImpl(StudentDao studentDao, CourseDao courseDao, CurriculumDao curriculumDao) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.curriculumDao = curriculumDao;
    }

    @Override
    public String generateShortReport() {
        StringBuilder report = new StringBuilder();
        students = studentDao.getAll();
        for (Student student : students) {
            endDate = TimeCalculations.getEndDate(student.getStartDate(), curriculumDao.getByName(student.getCurriculumName()).getDuration());
            durationBetweenEndDateAndCurrentDate = TimeCalculations.getDurationBetweenTwoDates(endDate, CURRENT_DATE);
            durationBetweenEndHourAndCurrentHour = Duration.between(endDate.plusDays(durationBetweenEndDateAndCurrentDate.toDays()), CURRENT_DATE).toHours();
            report.append(student.getLastName()).append(" ").append(student.getFirstName()).append(" (").append(student.getCurriculumName()).append(") - ");
            appendTrainingCompletedOrNotFinished(endDate, durationBetweenEndDateAndCurrentDate, durationBetweenEndHourAndCurrentHour, report);
        }
        return report.toString();
    }

    @Override
    public String generateFullReport() {
        StringBuilder report = new StringBuilder();
        students = studentDao.getAll();

        for (Student student : students) {
            endDate = TimeCalculations.getEndDate(student.getStartDate(), curriculumDao.getByName(student.getCurriculumName()).getDuration());
            durationBetweenEndDateAndCurrentDate = TimeCalculations.getDurationBetweenTwoDates(endDate, CURRENT_DATE);
            durationBetweenEndHourAndCurrentHour = Duration.between(endDate
                    .plusDays(durationBetweenEndDateAndCurrentDate.toDays()), CURRENT_DATE).toHours();
            List<String> courseNames = student.getCurriculum().getCourseNames();

            report.append("\nStudent: ").append(student.getLastName()).append(" ").append(student.getFirstName())
                    .append("\nCurriculum: ").append(student.getCurriculumName())
                    .append("\nCurriculum duration: ").append(curriculumDao.getByName(student.getCurriculumName()).getDuration().toHours())
                    .append("\nCourse              Duration (hours)").append("\n---------------------------");
            appendCourseNamesAndDurations(courseNames, report);
            report.append("\nStart date: ").append(student.getStartDate().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")))
                    .append("\nEnd date: ").append(endDate.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")));
            appendTrainingCompletedOrNotFinished(endDate, durationBetweenEndDateAndCurrentDate, durationBetweenEndHourAndCurrentHour, report);
            report.append("\n");
        }
        return report.toString();
    }

    private boolean isAfter(LocalDateTime endDate) {
        return CURRENT_DATE.isAfter(endDate);
    }

    private void appendTrainingCompletedOrNotFinished(LocalDateTime endDate, Duration durationBetweenEndDateAndCurrentDate, long durationBetweenEndHourAndCurrentHour, StringBuilder report) {
        if (isAfter(endDate)) {
            report.append("\n Training completed. ");
            if (durationBetweenEndDateAndCurrentDate.toDays() != 0)
                report.append(durationBetweenEndDateAndCurrentDate.toDays()).append(" d ");
            report.append(Math.abs(durationBetweenEndHourAndCurrentHour)).append(" hours have passed since the end.\n");
        } else {
            report.append("\n Training is not finished. ");
            if (durationBetweenEndDateAndCurrentDate.toDays() != 0)
                report.append(Math.abs(durationBetweenEndDateAndCurrentDate.toDays())).append(" d ");
            report.append(Math.abs(durationBetweenEndHourAndCurrentHour)).append(" hours are left until the end.\n");
        }
    }

    private void appendCourseNamesAndDurations(List<String> courseNames, StringBuilder report) {
        for (String name : courseNames) {
            Course course = courseDao.getByName(name);
            String courseName = course.getName();
            long courseDuration = course.getDuration().toHours();
            report.append("\n").append(courseName).append("               ");
            report.append(courseDuration);
        }
    }
}
