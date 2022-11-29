package com.boursinos.hrplatform.repositories.branch;

import com.boursinos.hrplatform.model.branch.Branch;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

public class BranchCustomRepositoryImpl implements BranchCustomRepository {

    EntityManager entityManager;

    public BranchCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public String saveBranch(Branch branch){
        branch.setCreatedAt(new Date());
        branch.setUpdatedAt(new Date());
        entityManager.persist(branch);
        return branch.getBranchId();
    }

}
