package com.boursinos.hrplatform.repositories.absence;

import javax.persistence.EntityManager;

public class AbsenceCustomRepositoryImpl implements AbsenceCustomRepository {

    EntityManager entityManager;

    public AbsenceCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


}
