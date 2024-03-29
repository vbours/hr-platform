package com.boursinos.hrplatform.service.absence;

import com.boursinos.hrplatform.model.entity.absence.Absence;
import java.util.List;
import java.util.Optional;

public interface AbsenceService {

    List<Absence> getAllAbsences();

    List<Absence> getAllAbsences(final int count);

    List<Absence> getAllAbsencesByEmployee(String employeeId);

    Optional<Absence> getAbsence(String absenceId);

    String saveAbsence(Absence absence, String employeeId);

    void deleteAbsence(String absenceId);

    Absence updateAbsence(String employeeId, String absenceId, Absence absence);

}
