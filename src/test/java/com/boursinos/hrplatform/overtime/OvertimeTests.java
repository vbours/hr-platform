package com.boursinos.hrplatform.overtime;

import com.boursinos.hrplatform.BaseTests;
import com.boursinos.hrplatform.model.branch.Branch;
import com.boursinos.hrplatform.model.employee.ContractType;
import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.model.employee.Gender;
import com.boursinos.hrplatform.model.overtime.Overtime;
import com.boursinos.hrplatform.service.branch.BranchService;
import com.boursinos.hrplatform.service.employee.EmployeeService;
import com.boursinos.hrplatform.service.overtime.OvertimeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OvertimeTests extends BaseTests {

    @Autowired
    private OvertimeService overtimeService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BranchService branchService;

    private int count = 1;

    private Overtime overtime;
    private Employee employee;
    private Branch branch;

    @Before
    public void init(){
        overtime = new Overtime(new Date(),2.5);
        employee = new Employee("firstname1","lastname2", Gender.MALE, 1987,
                "address1", "EEE", "76543", ContractType.FULL_TIME, 28,
                18, 100000);
        branch = new Branch("city","city","country");
    }

    @Test
    public void saveAndGetOvertimeTest(){
        String branchId = branchService.saveBranch(this.branch);
        String employeeId = employeeService.saveEmployee(this.employee,branchId);
        String overtimeId = overtimeService.saveOvertime(this.overtime,employeeId);
        Assert.assertNotEquals(overtimeId,null);

        Optional<Overtime> overtime =overtimeService.getOvertime(overtimeId);
        Assert.assertNotEquals(overtime,null);
    }

    @Test
    public void getAllOvertimesTest(){
        String branchId = branchService.saveBranch(this.branch);
        String employeeId = employeeService.saveEmployee(this.employee,branchId);
        overtimeService.saveOvertime(this.overtime,employeeId);
        List<Overtime> getAllOvertimes = overtimeService.getAllOvertimes();
        Assert.assertTrue(getAllOvertimes.size() > 0);
    }

    @Test
    public void getAllOvertimesPerEmployeeTest(){
        String branchId = branchService.saveBranch(this.branch);
        String employeeId = employeeService.saveEmployee(this.employee,branchId);
        overtimeService.saveOvertime(this.overtime,employeeId);
        List<Overtime> getAllOvertimesPerEmployee = overtimeService.getAllOvertimesByEmployee(employeeId);
        Assert.assertTrue(getAllOvertimesPerEmployee.size() > 0);
    }


}
