package com.boursinos.hrplatform.service;

import com.boursinos.hrplatform.model.Employee;
import com.boursinos.hrplatform.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public String saveEmployee(Employee employee){
        entityManager.persist(employee);
        return employee.getEmployeeId();
    }
}
