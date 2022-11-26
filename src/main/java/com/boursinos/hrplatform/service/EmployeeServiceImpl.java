package com.boursinos.hrplatform.service;

import com.boursinos.hrplatform.model.Employee;
import com.boursinos.hrplatform.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void saveEmployee(Employee employee){
       employeeRepository.save(employee);
    }
}
