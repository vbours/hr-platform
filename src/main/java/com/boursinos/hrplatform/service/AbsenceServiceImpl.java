package com.boursinos.hrplatform.service;

import com.boursinos.hrplatform.model.absence.Absence;
import com.boursinos.hrplatform.model.employee.Employee;
import com.boursinos.hrplatform.repositories.absence.AbsenceRepository;
import com.boursinos.hrplatform.repositories.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AbsenceServiceImpl implements AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Absence> getAllAbsences(){
        return absenceRepository.findAll();
    }

    @Override
    public List<Absence> getAllAbsencesByEmployee(String employeeId){
        Employee employee = employeeRepository.getById(employeeId);
        return absenceRepository.findByEmployee(employee);
    }

    @Override
    public Optional<Absence> getAbsence(String employeeId, String absenceId){
        return absenceRepository.findById(absenceId);
    }

    @Override
    public String saveAbsence(Absence absence, String employeeId){
        Employee employee = employeeRepository.getById(employeeId);
        absence.setCreatedAt(new Date());
        absence.setUpdatedAt(new Date());
        absence.setEmployee(employee);
        return absenceRepository.save(absence).getAbsenceId();
    }

    @Override
    public void deleteAbsence(String absenceId){
        absenceRepository.deleteById(absenceId);
    }

    @Override
    public Absence updateAbsence(String employeeId, String absenceId, Absence absence){
        Absence oldAbsence = absenceRepository.getById(absenceId);
        absence.setAbsenceId(absenceId);
        absence.setUpdatedAt(new Date());
        absence.setCreatedAt(oldAbsence.getCreatedAt());
        absenceRepository.save(absence);
        return absence;
    }

}
