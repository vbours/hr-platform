package com.boursinos.hrplatform.repositories.file;

import com.boursinos.hrplatform.model.report.File;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

public class FileCustomRepositoryImpl implements FileCustomRepository {

    EntityManager entityManager;

    public FileCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public String saveFile(File file){
        file.setCreatedAt(new Date());
        file.setUpdatedAt(new Date());
        entityManager.persist(file);
        return file.getEmployeeId();
    }

}
