package com.boursinos.hrplatform.repositories.overtime;

import javax.persistence.EntityManager;

public class OvertimeCustomRepositoryImpl implements OvertimeCustomRepository {

    EntityManager entityManager;

    public OvertimeCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


}
