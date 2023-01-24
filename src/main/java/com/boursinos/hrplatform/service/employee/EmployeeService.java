package com.boursinos.hrplatform.service.employee;

import com.boursinos.hrplatform.model.branch.Branch;
import com.boursinos.hrplatform.model.employee.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    List<Employee> getAllEmployees(int count);

    public Optional<Employee> getEmployee(String id);

    String saveEmployee(Employee employee, String branchId);

    void deleteEmployee(String id);

    Employee updateEmployee(String id, Employee employee);

    Employee createEmployee(final String firstname, final String lastname, final String gender,
                            final int yearOfBirth, final String address, final String postCode,
                            final String telNumber, final int totalHolidays, final int remainingHolidays,
                            final int salary);
}
