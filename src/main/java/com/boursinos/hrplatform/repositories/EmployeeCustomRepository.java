package com.boursinos.hrplatform.repositories;

import com.boursinos.hrplatform.model.employee.Employee;

public interface EmployeeCustomRepository{

    String saveEmployee(Employee employee);

    Employee updateEmployee(String id, Employee employee);

}
