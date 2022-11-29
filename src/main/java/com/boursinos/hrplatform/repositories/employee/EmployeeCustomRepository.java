package com.boursinos.hrplatform.repositories.employee;

import com.boursinos.hrplatform.model.employee.Employee;

public interface EmployeeCustomRepository{

    String saveEmployee(Employee employee, String branchId);

}
