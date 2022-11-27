package com.boursinos.hrplatform.service;

import com.boursinos.hrplatform.model.employee.Employee;

public interface EmployeeService {
    String saveEmployee(Employee employee);

    void deleteEmployee(String id);

}
