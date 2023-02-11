package com.boursinos.hrplatform.service.overtime;

import com.boursinos.hrplatform.model.entity.employee.Employee;
import com.boursinos.hrplatform.model.entity.overtime.Overtime;
import com.boursinos.hrplatform.repositories.employee.EmployeeRepository;
import com.boursinos.hrplatform.repositories.overtime.OvertimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OvertimeServiceImpl implements OvertimeService {

    @Autowired
    private OvertimeRepository overtimeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Overtime> getAllOvertimes(){
        return overtimeRepository.findAll();
    }

    @Override
    public List<Overtime> getAllOvertimesByEmployee(String employeeId){
        Employee employee = employeeRepository.getById(employeeId);
        return overtimeRepository.findByEmployee(employee);
    }

    @Override
    public Optional<Overtime> getOvertime(String overtimeId){
        return overtimeRepository.findById(overtimeId);
    }

    @Override
    public String saveOvertime(Overtime overtime, String employeeId){
        Employee employee = employeeRepository.getById(employeeId);
        overtime.setCreatedAt(new Date());
        overtime.setUpdatedAt(new Date());
        overtime.setEmployee(employee);
        return overtimeRepository.save(overtime).getOvertimeId();
    }

    @Override
    public void deleteOvertime(String overtimeId){
        overtimeRepository.deleteById(overtimeId);
    }

    @Override
    public Overtime updateOvertime(String overtimeId, Overtime overtime){
        Optional<Overtime> oldOvertime = this.getOvertime(overtimeId);
        overtime.setOvertimeId(overtimeId);
        overtime.setUpdatedAt(new Date());
        overtime.setCreatedAt(oldOvertime.get().getCreatedAt());
        overtimeRepository.save(overtime);
        return overtime;
    }

}
