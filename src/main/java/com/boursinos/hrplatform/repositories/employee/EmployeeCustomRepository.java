package com.boursinos.hrplatform.repositories.employee;

import com.boursinos.hrplatform.model.entity.employee.Employee;

import java.util.List;

public interface EmployeeCustomRepository{

    String saveEmployee(Employee employee, String branchId);

    public List getEmployeesPerBranch();

}
