package com.boursinos.hrplatform.repositories.file;

import com.boursinos.hrplatform.model.report.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, String>, FileCustomRepository {

}
