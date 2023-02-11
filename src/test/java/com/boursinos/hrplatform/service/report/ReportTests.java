package com.boursinos.hrplatform.service.report;

import com.boursinos.hrplatform.BaseTests;
import com.boursinos.hrplatform.model.entity.branch.Branch;
import com.boursinos.hrplatform.model.entity.employee.ContractType;
import com.boursinos.hrplatform.model.entity.employee.Employee;
import com.boursinos.hrplatform.model.entity.employee.Gender;
import com.boursinos.hrplatform.service.branch.BranchService;
import com.boursinos.hrplatform.service.employee.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportTests extends BaseTests {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private ReportService reportService;

    private Branch branch;

    private String branchId;
    private Employee employee;
    private Employee employee2;
    private Employee employee3;

//    private File file;

    private int count = 1;

    private List<Employee> employees = new ArrayList<>();

    public void init(){
        branch = new Branch("city","city","country");
        employee = new Employee("firstname1","lastname2", Gender.MALE, 1987, "address1", "EEE", "76543", ContractType.FULL_TIME, 28, 18, 100000);
        employees.add(employee);
        employee2 = new Employee("firstname1","lastname2", Gender.MALE, 1987, "address1", "EEE", "76543", ContractType.FULL_TIME, 28, 18, 100000);
        employees.add(employee2);
        employee3 = new Employee("firstname1","lastname2", Gender.MALE, 1987, "address1", "EEE", "76543", ContractType.FULL_TIME, 28, 18, 100000);
        employees.add(employee3);
//        file = new File("filename", "type", "hr-files");
    }

    @Before
    public void saveEmployees(){
        init();
        branchId = branchService.saveBranch(branch);
        for (Employee employee: employees){
           employeeService.saveEmployee(employee, branchId);
        }
    }

    @Test
    public void getAllEmployeesPerBranchTest(){
        Map<String,List<Employee>> employeeMap = reportService.getEmployeesPerBranchMap();
        List<Employee> employeeList = employeeMap.get(branchId);
        Assert.assertEquals(employeeList.size(),3);
    }

    @Test
    public void getAllEmployeesPerBranchCountTest(){
        Map<String,Integer> branchMap = reportService.getTotalEmployeesPerBranchMap();
        Integer totalEmployee = branchMap.get(branchId);
        Assert.assertTrue(totalEmployee == 3);
    }


    @Test
    public void getEmployeesTotalSalaryPerBranchTest(){
        Map<String,Integer> salaryMap = reportService.getEmployeesTotalSalaryPerBranchMap();
        Integer totalSalary = salaryMap.get(branchId);
        Assert.assertTrue(totalSalary == 300000);
    }

}
