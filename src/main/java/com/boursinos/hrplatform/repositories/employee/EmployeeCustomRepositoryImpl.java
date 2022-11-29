package com.boursinos.hrplatform.repositories.employee;

import com.boursinos.hrplatform.model.branch.Branch;
import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.repositories.branch.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {

    EntityManager entityManager;

    @Autowired
    private BranchRepository branchRepository;

    public EmployeeCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public String saveEmployee(Employee employee, String branchId){
        Branch branch = entityManager.find(Branch.class, branchId);
        employee.setCreatedAt(new Date());
        employee.setUpdatedAt(new Date());
        employee.setBranch(branch);
        entityManager.persist(employee);
        return employee.getEmployeeId();
    }

}
