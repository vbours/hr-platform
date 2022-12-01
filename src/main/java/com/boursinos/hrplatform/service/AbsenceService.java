package com.boursinos.hrplatform.service;

import com.boursinos.hrplatform.model.absence.Absence;
import java.util.List;
import java.util.Optional;

public interface AbsenceService {

    List<Absence> getAllAbsences();

    List<Absence> getAllAbsencesByEmployee(String employeeId);

    Optional<Absence> getAbsence(String absence_id);

    String saveAbsence(Absence absence, String employee_id);

    void deleteAbsence(String absence_id);

    Absence updateAbsence(String employee_id, String absence_id, Absence absence);

}
