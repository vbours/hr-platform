package com.boursinos.hrplatform.repositories.employee;

import com.boursinos.hrplatform.model.entity.branch.Branch;
import com.boursinos.hrplatform.model.entity.employee.Employee;
import com.boursinos.hrplatform.repositories.branch.BranchRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {

    EntityManager entityManager;

    static final Logger logger = Logger.getLogger(EmployeeCustomRepositoryImpl.class);


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

    @Override
    public List getEmployeesPerBranch() {
        Query query = entityManager.createQuery("SELECT e FROM Employee e INNER JOIN e.branch b");
        return query.getResultList();
    }

}
