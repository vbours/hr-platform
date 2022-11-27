package com.boursinos.hrplatform.repositories;

import com.boursinos.hrplatform.model.employee.Employee;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {

    EntityManager entityManager;

    public EmployeeCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public String saveEmployee(Employee employee){
        employee.setCreatedAt(new Date());
        employee.setUpdatedAt(new Date());
        entityManager.persist(employee);
        return employee.getEmployeeId();
    }

}
