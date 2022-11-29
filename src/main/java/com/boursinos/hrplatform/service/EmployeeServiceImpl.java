package com.boursinos.hrplatform.service;

import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.repositories.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees(){
       return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployee(String id){
        return employeeRepository.findById(id);
    }

    @Transactional
    public String saveEmployee(Employee employee, String branchId){
        return employeeRepository.saveEmployee(employee, branchId);
    }

    @Override
    public void deleteEmployee(String id){
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(String id, Employee employee){
        Employee oldEmployee = employeeRepository.getById(id);
        employee.setEmployeeId(id);
        employee.setUpdatedAt(new Date());
        employee.setCreatedAt(oldEmployee.getCreatedAt());
        employeeRepository.save(employee);
        return employee;
    }

}
