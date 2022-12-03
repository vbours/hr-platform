package com.boursinos.hrplatform.service;

import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.repositories.employee.EmployeeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService{
    static final Logger logger = Logger.getLogger(ReportServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Map<String, List<Employee>> getEmployeesPerBranchMap() {
        List<Employee> resultList = employeeRepository.getEmployeesPerBranch();
        Map<String, List<Employee>> branchMap = new HashMap<>();

        for (Employee employee : resultList) {
            if (!branchMap.containsKey(employee.getBranch().getBranchId())) {
                branchMap.put(employee.getBranch().getBranchId(), new ArrayList<>());
            }
            branchMap.get(employee.getBranch().getBranchId()).add(employee);
        }
        logger.info(branchMap.toString());
        return branchMap;
    }

    @Override
    public Map<String, Integer> getTotalEmployeesPerBranchMap() {
        List<Employee> resultList = employeeRepository.getEmployeesPerBranch();
        Map<String, Integer> employeeCounterMap = new HashMap<>();

        for (Employee employee : resultList) {
            employeeCounterMap.put(employee.getBranch().getBranchId(), employeeCounterMap.getOrDefault(employee.getBranch().getBranchId(), 0) + 1);

        }
        logger.info(employeeCounterMap.toString());
        return employeeCounterMap;
    }
}
