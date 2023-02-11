package com.boursinos.hrplatform.repositories.overtime;

import com.boursinos.hrplatform.model.entity.employee.Employee;
import com.boursinos.hrplatform.model.entity.overtime.Overtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OvertimeRepository extends JpaRepository<Overtime, String>, OvertimeCustomRepository {

    @Query("select s from Overtime s where s.employee = ?1")
    List<Overtime> findByEmployee(Employee employee);

}
