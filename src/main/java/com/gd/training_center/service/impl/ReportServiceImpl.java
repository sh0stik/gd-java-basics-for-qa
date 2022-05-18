package com.gd.training_center.service.impl;

import com.gd.training_center.dao.StudentDao;
import com.gd.training_center.model.Report;
import com.gd.training_center.model.ReportType;
import com.gd.training_center.model.Student;
import com.gd.training_center.service.ReportService;

import java.time.LocalDateTime;
import java.util.List;

public class ReportServiceImpl implements ReportService {
    private final StudentDao studentDao;
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now();
    private final ReportType reportType;


    public ReportServiceImpl(StudentDao studentDao, ReportType reportType) {
        this.studentDao = studentDao;
        this.reportType = reportType;
    }


    @Override
    public String generateReport() {
        List<Student> students = studentDao.getAll();
        StringBuilder allStudentsReport = new StringBuilder();

        for (Student student : students) {
            Report studentReport = new Report(student, CURRENT_DATE, reportType);
            allStudentsReport.append(studentReport.getReport());
        }
        return allStudentsReport.toString();
    }


}
