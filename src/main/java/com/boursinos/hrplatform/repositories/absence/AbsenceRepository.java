package com.boursinos.hrplatform.repositories.absence;

import com.boursinos.hrplatform.model.absence.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, String>, AbsenceCustomRepository {

}
