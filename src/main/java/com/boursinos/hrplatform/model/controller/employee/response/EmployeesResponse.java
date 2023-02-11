package com.boursinos.hrplatform.model.controller.employee.response;

import com.boursinos.hrplatform.model.entity.employee.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EmployeesResponse {
    private List<Employee> allEmployees;

    public EmployeesResponse(List<Employee> allEmployees) {
        this.allEmployees = allEmployees;
    }
}
