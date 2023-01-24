package com.boursinos.hrplatform.service.report;

import com.boursinos.hrplatform.model.employee.Employee;

import java.util.List;
import java.util.Map;

public interface ReportService {
    Map<String, List<Employee>> getEmployeesPerBranchMap();
    Map<String, Integer> getTotalEmployeesPerBranchMap();
    Map<String, Integer> getEmployeesTotalSalaryPerBranchMap();

    }
