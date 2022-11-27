package com.boursinos.hrplatform.service;

import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public String saveEmployee(Employee employee){
        return employeeRepository.saveEmployee(employee);
    }

    @Override
    public void deleteEmployee(String id){
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(String id, Employee employee){
        return employeeRepository.updateEmployee(id, employee);
    }

}
