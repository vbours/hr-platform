package com.boursinos.hrplatform.graphql.mutation;

import com.boursinos.hrplatform.model.branch.Branch;
import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.service.BranchService;
import com.boursinos.hrplatform.service.EmployeeService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMutation implements GraphQLMutationResolver {

    @Autowired
    private EmployeeService employeeService;

    public Employee createEmployee(final String firstname, final String lastname, final String gender,
                                 final int yearOfBirth, final String address, final String postCode,
                                 final String telNumber, final int totalHolidays, final int remainingHolidays,
                                 final int salary) {

        return this.employeeService.createEmployee(firstname,lastname,gender,yearOfBirth,address,postCode,
                telNumber,totalHolidays,remainingHolidays,salary);
    }
}
