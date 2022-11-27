package com.boursinos.hrplatform.repositories;

import com.boursinos.hrplatform.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>, EmployeeCustomRepository {

}
