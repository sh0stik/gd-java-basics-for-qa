package com.gd.training_center.service;

import com.gd.training_center.model.ReportType;

public interface ReportService {

    String generateReport(ReportType reportType);
}
