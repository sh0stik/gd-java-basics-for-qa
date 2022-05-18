package com.gd.training_center.model;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.Math.abs;

@Data
public class Report {
    private Student student;
    private LocalDateTime currentDate;
    private ReportType reportType;
    private Duration endToCurrentDateDuration;
    private long fullDaysBetween;
    private long remainingHours;

    public Report(Student student, LocalDateTime currentDate, ReportType reportType) {
        this.student = student;
        this.currentDate = currentDate;
        this.reportType = reportType;
        this.endToCurrentDateDuration = Duration.between(student.getEndDate(), currentDate);
        // Calling abs() since Duration can be negative
        this.fullDaysBetween = abs(endToCurrentDateDuration.toDays());
        this.remainingHours = abs(endToCurrentDateDuration.minusDays(fullDaysBetween).toHours());
    }


    public String getReport() {
        StringBuilder result = new StringBuilder();
        LocalDateTime endDate = student.getEndDate();
        switch (reportType) {
            case SHORT:
                result.append(student.getFullName()).append(" ").append(" (").append(student.getCurriculum().getName()).append(") - ");
                break;
            case FULL:
                result.append("\nStudent: ").append(student.getFullName())
                        .append("\nCurriculum: ").append(student.getCurriculum().getName())
                        .append("\nCurriculum duration: ").append(student.getCurriculum().calculateDuration().toHours())
                        .append("\nCourse              Duration (hours)").append("\n---------------------------");
                appendCourseNamesAndDurations(student.getCurriculum().getCourseList(), result);
                result.append("\nStart date: ").append(student.getStartDate().format(DateTimeFormatter.ofPattern("E, MMM dd " +
                                "yyyy")))
                        .append("\nEnd date: ").append(endDate.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")));
                break;
        }
        appendTrainingCompletedOrNotFinished(result, student.getEndDate());
        result.append("\n");
        return result.toString();
    }

    private void appendCourseNamesAndDurations(List<Course> courses, StringBuilder result) {
        for (Course course : courses) {
            String courseName = course.getName();
            long courseDuration = course.getDuration().toHours();
            result.append("\n").append(courseName).append("              ").append(courseDuration);
        }
    }

    private void appendTrainingCompletedOrNotFinished(StringBuilder stringBuilder, LocalDateTime endDate) {
        if (currentDate.isAfter(endDate)) {
            stringBuilder.append("\nTraining completed. ");
            if (fullDaysBetween != 0)
                stringBuilder.append(fullDaysBetween).append(" d ");
            stringBuilder.append(remainingHours).append(" hours have passed since the end.\n");
        } else {
            stringBuilder.append("\nTraining is not finished. ");
            if (fullDaysBetween != 0)
                stringBuilder.append(fullDaysBetween).append(" d ");
            stringBuilder.append(remainingHours).append(" hours are left until the end.\n");
        }
    }
}
