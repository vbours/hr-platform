package com.boursinos.hrplatform.service.overtime;

import com.boursinos.hrplatform.model.entity.overtime.Overtime;

import java.util.List;
import java.util.Optional;

public interface OvertimeService {

    List<Overtime> getAllOvertimes();

    List<Overtime> getAllOvertimesByEmployee(String employeeId);

    Optional<Overtime> getOvertime(String overtimeId);

    String saveOvertime(Overtime overtime, String overtimeId);

    void deleteOvertime(String overtimeId);

    Overtime updateOvertime(String overtimeId, Overtime overtime);

}
