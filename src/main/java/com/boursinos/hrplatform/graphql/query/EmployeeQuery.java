package com.boursinos.hrplatform.graphql.query;

import com.boursinos.hrplatform.model.entity.employee.Employee;
import com.boursinos.hrplatform.service.employee.EmployeeService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeQuery implements GraphQLQueryResolver {

    @Autowired
    private EmployeeService employeeService;

    public List<Employee> getEmployees(final int count) {
        return this.employeeService.getAllEmployees(count);
    }

    public Optional<Employee> getEmployee(final String id) {
        return this.employeeService.getEmployee(id);
    }
}
