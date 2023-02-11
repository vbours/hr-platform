package com.boursinos.hrplatform.repositories.absence;

import com.boursinos.hrplatform.model.entity.absence.Absence;
import com.boursinos.hrplatform.model.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, String>, AbsenceCustomRepository {
    @Query("select s from Absence s where s.employee = ?1")
    List<Absence> findByEmployee(Employee employee);

}
