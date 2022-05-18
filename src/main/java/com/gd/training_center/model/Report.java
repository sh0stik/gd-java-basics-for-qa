package com.gd.training_center.model;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.gd.training_center.model.ReportType.SHORT_REPORT;
import static java.lang.Math.abs;

@Data
public class Report {
    private Student student;
    private LocalDateTime currentDate;
    private ReportType reportType;

    public Report(Student student, LocalDateTime currentDate, ReportType reportType) {
        this.student = student;
        this.currentDate = currentDate;
        this.reportType = reportType;
    }


    public String getReport() {
        StringBuilder result = new StringBuilder();
        LocalDateTime endDate = student.getEndDate();
        Duration durationBetweenEndDateAndCurrentDate = Duration.between(endDate, currentDate);
//      Calling abs() since Duration can be negative
        long fullDaysBetween = abs(durationBetweenEndDateAndCurrentDate.toDays());
        if (reportType.equals(SHORT_REPORT)) {
            result.append(student.getFullName()).append(" ").append(" (").append(student.getCurriculum().getName()).append(") - ");
            appendTrainingCompletedOrNotFinished(result, student.getEndDate(), fullDaysBetween);
        } else {
            result.append("\nStudent: ").append(student.getFullName())
                    .append("\nCurriculum: ").append(student.getCurriculum().getName())
                    .append("\nCurriculum duration: ").append(student.getCurriculum().calculateDuration().toHours())
                    .append("\nCourse              Duration (hours)").append("\n---------------------------");
            appendCourseNamesAndDurations(student.getCurriculum().getCourseList(), result);
            result.append("\nStart date: ").append(student.getStartDate().format(DateTimeFormatter.ofPattern("E, MMM dd " +
                            "yyyy")))
                    .append("\nEnd date: ").append(endDate.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy")));
            appendTrainingCompletedOrNotFinished(result, student.getEndDate(), fullDaysBetween);
            result.append("\n");
        }
        return result.toString();
    }

    private void appendCourseNamesAndDurations(List<Course> courses, StringBuilder result) {
        for (Course course : courses) {
            String courseName = course.getName();
            long courseDuration = course.getDuration().toHours();
            result.append("\n").append(courseName).append("              ").append(courseDuration);
        }
    }

    private void appendTrainingCompletedOrNotFinished(StringBuilder stringBuilder, LocalDateTime endDate,
                                                      long fullDaysBetween) {
        long reminderHours;
        if (currentDate.isAfter(endDate)) {
            reminderHours =
                    abs(Duration.between(endDate.plusDays(fullDaysBetween), currentDate).toHours());
            stringBuilder.append("\nTraining completed. ");
            if (fullDaysBetween != 0)
                stringBuilder.append(fullDaysBetween).append(" d ");
            stringBuilder.append(reminderHours).append(" hours have passed since the end.\n");
        } else {
            reminderHours =
                    abs(Duration.between(currentDate.plusDays(fullDaysBetween), endDate).toHours());
            stringBuilder.append("\nTraining is not finished. ");
            if (fullDaysBetween != 0)
                stringBuilder.append(fullDaysBetween).append(" d ");
            stringBuilder.append(reminderHours).append(" hours are left until the end.\n");
        }
    }
}
