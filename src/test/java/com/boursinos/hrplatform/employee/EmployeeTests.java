package com.boursinos.hrplatform.employee;

import com.boursinos.hrplatform.BaseTests;
import com.boursinos.hrplatform.model.branch.Branch;
import com.boursinos.hrplatform.model.employee.ContractType;
import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.model.employee.Gender;
import com.boursinos.hrplatform.service.BranchService;
import com.boursinos.hrplatform.service.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EmployeeTests extends BaseTests {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BranchService branchService;

    private Branch branch;
    private Employee employee;
    private int count = 1;

    @Before
    public void init(){
        branch = new Branch("city","city","country");
        employee = new Employee("firstname1","lastname2", Gender.MALE, 1987, "address1", "EEE", "76543", ContractType.FULL_TIME, 28, 18, 100000);
    }

    @Test
    public void saveAndGetEmployeeTest(){
        String branchId = branchService.saveBranch(branch);
        String employeeId = employeeService.saveEmployee(employee, branchId);
        Assert.assertNotEquals(employeeId,null);

        Optional<Employee> employee1 = employeeService.getEmployee(employeeId);
        Assert.assertNotEquals(employee1,null);
    }

    @Test
    public void getAllEmployeesTest(){
        String branchId = branchService.saveBranch(branch);
        employeeService.saveEmployee(employee,branchId);
        List<Employee> getAllEmployees = employeeService.getAllEmployees();
        Assert.assertEquals(getAllEmployees.size(),2);
    }

    @Test
    public void getAllEmployeesCountTest(){
        List<Employee> getAllEmployees = employeeService.getAllEmployees(count);
        Assert.assertEquals(getAllEmployees.size(),1);
    }


}
