package com.boursinos.hrplatform.service.employee;

import com.boursinos.hrplatform.model.entity.branch.Branch;
import com.boursinos.hrplatform.model.entity.employee.Employee;
import com.boursinos.hrplatform.model.entity.employee.Gender;
import com.boursinos.hrplatform.repositories.branch.BranchRepository;
import com.boursinos.hrplatform.repositories.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<Employee> getAllEmployees(){
       return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllEmployees(int count) {
        return this.employeeRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> getEmployee(String id){
        return employeeRepository.findById(id);
    }

    @Override
    public String saveEmployee(Employee employee, String branchId){
        Branch branch = branchRepository.getById(branchId);
        employee.setCreatedAt(new Date());
        employee.setUpdatedAt(new Date());
        employee.setBranch(branch);
        return employeeRepository.save(employee).getEmployeeId();
    }

    @Override
    public void deleteEmployee(String id){
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(String id, Employee employee){
        Optional<Employee> oldEmployee = this.getEmployee(id);
        employee.setEmployeeId(id);
        employee.setUpdatedAt(new Date());
        employee.setCreatedAt(oldEmployee.get().getCreatedAt());
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee createEmployee(String firstname, String lastname, String gender, int yearOfBirth, String address, String postCode, String telNumber, int totalHolidays, int remainingHolidays, int salary) {
        final Employee employee = new Employee();
        employee.setFirstname(firstname);
        employee.setLastname(lastname);
        employee.setGender(Gender.MALE);
        employee.setYearOfBirth(yearOfBirth);
        employee.setAddress(address);
        employee.setPostCode(postCode);
        employee.setTelNumber(telNumber);
        employee.setTotalHolidays(totalHolidays);
        employee.setRemainingHolidays(remainingHolidays);
        employee.setSalary(salary);
        employee.setCreatedAt(new Date());
        employee.setUpdatedAt(new Date());
        return this.employeeRepository.save(employee);
    }

}
