package com.boursinos.hrplatform.service;

import com.boursinos.hrplatform.model.employee.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    public Optional<Employee> getEmployee(String id);
    String saveEmployee(Employee employee);

    void deleteEmployee(String id);

    Employee updateEmployee(String id, Employee employee);

}
